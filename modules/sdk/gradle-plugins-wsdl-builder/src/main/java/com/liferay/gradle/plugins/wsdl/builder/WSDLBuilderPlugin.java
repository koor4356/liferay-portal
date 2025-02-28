/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gradle.plugins.wsdl.builder;

import com.liferay.gradle.util.FileUtil;
import com.liferay.gradle.util.GradleUtil;

import groovy.lang.Closure;

import java.io.File;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskInputs;
import org.gradle.api.tasks.TaskOutputs;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.api.tasks.compile.JavaCompile;

/**
 * @author Andrea Di Giorgi
 */
public class WSDLBuilderPlugin implements Plugin<Project> {

	public static final String BUILD_WSDL_TASK_NAME = "buildWSDL";

	public static final String CONFIGURATION_NAME = "wsdlBuilder";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, JavaPlugin.class);

		BuildWSDLTask buildWSDLTask = _addTaskBuildWSDL(project);

		final Configuration wsdlBuilderConfiguration =
			_addConfigurationWSDLBuilder(project, buildWSDLTask);

		project.afterEvaluate(
			new Action<Project>() {

				@Override
				public void execute(Project project) {
					_configureTasksBuildWSDL(project, wsdlBuilderConfiguration);
				}

			});
	}

	private Configuration _addConfigurationWSDLBuilder(
		final Project project, final BuildWSDLTask buildWSDLTask) {

		Configuration configuration = GradleUtil.addConfiguration(
			project, CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					_addDependenciesWSDLBuilder(project, buildWSDLTask);
				}

			});

		configuration.setDescription(
			"Configures Apache Axis for generating WSDL client stubs.");
		configuration.setVisible(false);

		if (buildWSDLTask.getAxisVersion() == 2) {
			Configuration compileConfiguration = GradleUtil.getConfiguration(
				project, JavaPlugin.COMPILE_CONFIGURATION_NAME);

			configuration.extendsFrom(compileConfiguration);
		}

		return configuration;
	}

	private void _addDependenciesWSDLBuilder(
		Project project, BuildWSDLTask buildWSDLTask) {

		if (buildWSDLTask.getAxisVersion() == 2) {
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "org.apache.axis2", "axis2",
				"1.7.9");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "org.apache.axis2",
				"axis2-xmlbeans", "1.7.9");
		}
		else {
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "axis", "axis-wsdl4j", "1.5.1");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "com.liferay", "org.apache.axis",
				"1.4.LIFERAY-PATCHED-1");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "commons-discovery",
				"commons-discovery", "0.2");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "commons-logging",
				"commons-logging", "1.0.4");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "javax.activation", "activation",
				"1.1");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "com.sun.mail", "jakarta.mail",
				"1.6.6");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "org.apache.axis", "axis-jaxrpc",
				"1.4");
			GradleUtil.addDependency(
				project, CONFIGURATION_NAME, "org.apache.axis", "axis-saaj",
				"1.4");
		}
	}

	private BuildWSDLTask _addTaskBuildWSDL(Project project) {
		final BuildWSDLTask buildWSDLTask = GradleUtil.addTask(
			project, BUILD_WSDL_TASK_NAME, BuildWSDLTask.class);

		buildWSDLTask.setDescription("Generates WSDL client stubs.");

		buildWSDLTask.setDestinationDir(
			new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					if (buildWSDLTask.isBuildLibs()) {
						return "lib";
					}

					return _getJavaDir(buildWSDLTask.getProject());
				}

			});

		buildWSDLTask.setGroup(BasePlugin.BUILD_GROUP);
		buildWSDLTask.setIncludes(Collections.singleton("**/*.wsdl"));
		buildWSDLTask.setSource("wsdl");

		PluginContainer pluginContainer = project.getPlugins();

		pluginContainer.withType(
			WarPlugin.class,
			new Action<WarPlugin>() {

				@Override
				public void execute(WarPlugin warPlugin) {
					_configureTaskBuildWSDLForWarPlugin(buildWSDLTask);
				}

			});

		return buildWSDLTask;
	}

	private Task _addTaskBuildWSDLCompile(
		BuildWSDLTask buildWSDLTask, FileCollection classpath, File inputFile,
		File tmpDir, Task generateTask) {

		String taskName = GradleUtil.getTaskName(
			buildWSDLTask.getName() + "Compile", inputFile);

		JavaCompile javaCompile = GradleUtil.addTask(
			buildWSDLTask.getProject(), taskName, JavaCompile.class);

		javaCompile.setClasspath(classpath);

		File tmpBinDir = new File(tmpDir, "bin");

		javaCompile.setDestinationDir(tmpBinDir);

		javaCompile.setSource(generateTask.getOutputs());

		return javaCompile;
	}

	private Task _addTaskBuildWSDLGenerate(
		BuildWSDLTask buildWSDLTask, FileCollection classpath, File inputFile,
		final File destinationDir, boolean deleteDestinationDir) {

		String taskName = GradleUtil.getTaskName(
			buildWSDLTask.getName() + "Generate", inputFile);

		JavaExec javaExec = GradleUtil.addTask(
			buildWSDLTask.getProject(), taskName, JavaExec.class);

		GenerateOptions generateOptions = buildWSDLTask.getGenerateOptions();

		javaExec.args(generateOptions.getArgs());

		if (buildWSDLTask.getAxisVersion() == 2) {
			javaExec.args("--output", FileUtil.getAbsolutePath(destinationDir));
			javaExec.args("-uri", FileUtil.getAbsolutePath(inputFile));

			javaExec.setMain("org.apache.axis2.wsdl.WSDL2Code");
		}
		else {
			String outputPath = FileUtil.getAbsolutePath(destinationDir);

			javaExec.args("--output=" + outputPath);

			javaExec.args(FileUtil.getAbsolutePath(inputFile));

			javaExec.setMain("org.apache.axis.wsdl.WSDL2Java");
		}

		if (deleteDestinationDir) {
			javaExec.doFirst(
				new Action<Task>() {

					@Override
					public void execute(Task task) {
						Project project = task.getProject();

						project.delete(destinationDir);
					}

				});
		}

		javaExec.setClasspath(classpath);

		TaskInputs taskInputs = javaExec.getInputs();

		taskInputs.file(inputFile);

		TaskOutputs taskOutputs = javaExec.getOutputs();

		taskOutputs.dir(destinationDir);

		return javaExec;
	}

	private Jar _addTaskBuildWSDLJar(
		BuildWSDLTask buildWSDLTask, File inputFile, Task compileTask,
		final Task generateTask) {

		Project project = buildWSDLTask.getProject();

		Jar jar = GradleUtil.addTask(
			project, GradleUtil.getTaskName(buildWSDLTask.getName(), inputFile),
			Jar.class);

		jar.from(compileTask.getOutputs());

		GenerateOptions generateOptions = buildWSDLTask.getGenerateOptions();

		if ((buildWSDLTask.getAxisVersion() == 2) &&
			(generateOptions.getDatabinding() ==
				GenerateOptions.Databinding.XMLBEANS)) {

			TaskOutputs taskOutputs = generateTask.getOutputs();

			FileCollection fileCollection = taskOutputs.getFiles();

			final File dir = fileCollection.getSingleFile();

			jar.from(
				new Callable<File>() {

					@Override
					public File call() throws Exception {
						return new File(dir, "schemaorg_apache_xmlbeans");
					}

				},
				new Closure<Void>(project) {

					@SuppressWarnings("unused")
					public void doCall(CopySpec copySpec) {
						copySpec.into("schemaorg_apache_xmlbeans");
					}

				});
		}

		if (buildWSDLTask.isIncludeSource()) {
			jar.into(
				"OSGI-OPT/src",
				new Closure<Void>(project) {

					@SuppressWarnings("unused")
					public void doCall(CopySpec copySpec) {
						copySpec.from(generateTask.getOutputs());
					}

				});
		}

		jar.setDestinationDir(buildWSDLTask.getDestinationDir());

		String wsdlName = FileUtil.stripExtension(inputFile.getName());

		jar.setArchiveName(wsdlName + "-ws.jar");

		return jar;
	}

	private void _addTaskBuildWSDLTasks(
		BuildWSDLTask buildWSDLTask, File inputFile,
		Configuration wsdlBuilderConfiguration) {

		Project project = buildWSDLTask.getProject();

		if (buildWSDLTask.isBuildLibs()) {
			String tmpDirName =
				"build-wsdl/" + FileUtil.stripExtension(inputFile.getName());

			File tmpDir = new File(project.getBuildDir(), tmpDirName);

			File tmpSrcDir = new File(tmpDir, "src");

			Task generateTask = _addTaskBuildWSDLGenerate(
				buildWSDLTask, wsdlBuilderConfiguration, inputFile, tmpSrcDir,
				true);

			Task compileTask = _addTaskBuildWSDLCompile(
				buildWSDLTask, wsdlBuilderConfiguration, inputFile, tmpDir,
				generateTask);

			Jar jar = _addTaskBuildWSDLJar(
				buildWSDLTask, inputFile, compileTask, generateTask);

			buildWSDLTask.dependsOn(jar);

			TaskOutputs taskOutputs = buildWSDLTask.getOutputs();

			taskOutputs.file(jar.getArchivePath());
		}
		else {
			Task generateTask = _addTaskBuildWSDLGenerate(
				buildWSDLTask, wsdlBuilderConfiguration, inputFile,
				buildWSDLTask.getDestinationDir(), false);

			buildWSDLTask.dependsOn(generateTask);
		}
	}

	private void _configureTaskBuildWSDL(
		final BuildWSDLTask buildWSDLTask, Copy processResourcesTask,
		Configuration wsdlBuilderConfiguration) {

		FileCollection fileCollection = buildWSDLTask.getSource();

		if (fileCollection.isEmpty()) {
			return;
		}

		Project project = buildWSDLTask.getProject();

		for (File inputFile : fileCollection) {
			_addTaskBuildWSDLTasks(
				buildWSDLTask, inputFile, wsdlBuilderConfiguration);
		}

		if (buildWSDLTask.isBuildLibs()) {
			TaskOutputs taskOutputs = buildWSDLTask.getOutputs();

			GradleUtil.addDependency(
				project, JavaPlugin.COMPILE_CONFIGURATION_NAME,
				taskOutputs.getFiles());
		}

		if (buildWSDLTask.isIncludeWSDLs() && (processResourcesTask != null)) {
			processResourcesTask.into(
				"wsdl",
				new Closure<Void>(project) {

					@SuppressWarnings("unused")
					public void doCall(CopySpec copySpec) {
						copySpec.from(buildWSDLTask.getSource());
					}

				});
		}
	}

	private void _configureTaskBuildWSDLForWarPlugin(
		final BuildWSDLTask buildWSDLTask) {

		buildWSDLTask.setDestinationDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					if (buildWSDLTask.isBuildLibs()) {
						return new File(
							_getWebAppDir(buildWSDLTask.getProject()),
							"WEB-INF/lib");
					}

					return _getJavaDir(buildWSDLTask.getProject());
				}

			});

		buildWSDLTask.setSource(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return new File(
						_getWebAppDir(buildWSDLTask.getProject()),
						"WEB-INF/wsdl");
				}

			});
	}

	private void _configureTasksBuildWSDL(
		Project project, final Configuration wsdlBuilderConfiguration) {

		TaskContainer taskContainer = project.getTasks();

		final Copy processResourcesTask = (Copy)taskContainer.findByName(
			JavaPlugin.PROCESS_RESOURCES_TASK_NAME);

		taskContainer.withType(
			BuildWSDLTask.class,
			new Action<BuildWSDLTask>() {

				@Override
				public void execute(BuildWSDLTask buildWSDLTask) {
					_configureTaskBuildWSDL(
						buildWSDLTask, processResourcesTask,
						wsdlBuilderConfiguration);
				}

			});
	}

	private File _getJavaDir(Project project) {
		SourceSet sourceSet = GradleUtil.getSourceSet(
			project, SourceSet.MAIN_SOURCE_SET_NAME);

		return _getSrcDir(sourceSet.getJava());
	}

	private File _getSrcDir(SourceDirectorySet sourceDirectorySet) {
		Set<File> srcDirs = sourceDirectorySet.getSrcDirs();

		Iterator<File> iterator = srcDirs.iterator();

		return iterator.next();
	}

	private File _getWebAppDir(Project project) {
		WarPluginConvention warPluginConvention = GradleUtil.getConvention(
			project, WarPluginConvention.class);

		return warPluginConvention.getWebAppDir();
	}

}