<#if entries?? && entries?has_content && (entries?size >= 2) >
	<#assign rootNavigationItem = entries[1] />

	<div class="nav-menu nav-menu-style-${bulletStyle}">
		<h2>
			<a href="${rootNavigationItem.getRegularURL()}" ${rootNavigationItem.getTarget()}>${rootNavigationItem.getName()}</a>
		</h2>

		<@displayChildNavigation childLayoutLevel=2 childNavigationItems=rootNavigationItem.getChildren() includeAllChildEntries=false />
	</div>
</#if>

<#macro displayChildNavigation
	childLayoutLevel
	childNavigationItems
	includeAllChildEntries
>
	<#if childNavigationItems?has_content>
		<ul class="layouts level-${childLayoutLevel}">
			<#list childNavigationItems as childNavigationItem>
				<li class="open">
					<a href="${childNavigationItem.getRegularURL()!""} ">${htmlUtil.escape(childNavigationItem.getName())}</a>

					<#if includeAllChildEntries || childNavigationItem.isBelongsToNavigationEntries(entries) >
						<@displayChildNavigation childLayoutLevel=(childLayoutLevel + 1) childNavigationItems=childNavigationItem.getChildren() includeAllChildEntries=includeAllChildEntries/>
					</#if>
				</li>
			</#list>
		</ul>
	</#if>
</#macro>