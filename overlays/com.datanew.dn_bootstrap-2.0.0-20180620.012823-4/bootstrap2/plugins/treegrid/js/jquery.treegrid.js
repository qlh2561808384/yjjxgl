(function(b){var a={initTree:function(c){var d=b.extend({},this.treegrid.defaults,c);return this.each(function(){var e=b(this);e.treegrid("setTreeContainer",b(this));e.treegrid("setSettings",d);d.getRootNodes.apply(this,[b(this)]).treegrid("initNode",d);e.treegrid("getRootNodes").treegrid("render")})},initNode:function(c){return this.each(function(){var d=b(this);d.treegrid("setTreeContainer",c.getTreeGridContainer.apply(this));d.treegrid("getChildNodes").treegrid("initNode",c);d.treegrid("initExpander").treegrid("initIndent").treegrid("initEvents").treegrid("initState").treegrid("initChangeEvent").treegrid("initSettingsEvents")})},initChangeEvent:function(){var c=b(this);c.on("change",function(){var d=b(this);d.treegrid("render");if(d.treegrid("getSetting","saveState")){d.treegrid("saveState")}});return c},initEvents:function(){var c=b(this);c.on("collapse",function(){var d=b(this);d.removeClass("treegrid-expanded");d.addClass("treegrid-collapsed")});c.on("expand",function(){var d=b(this);d.removeClass("treegrid-collapsed");d.addClass("treegrid-expanded")});return c},initSettingsEvents:function(){var c=b(this);c.on("change",function(){var d=b(this);if(typeof(d.treegrid("getSetting","onChange"))==="function"){d.treegrid("getSetting","onChange").apply(d)}});c.on("collapse",function(){var d=b(this);if(typeof(d.treegrid("getSetting","onCollapse"))==="function"){d.treegrid("getSetting","onCollapse").apply(d)}});c.on("expand",function(){var d=b(this);if(typeof(d.treegrid("getSetting","onExpand"))==="function"){d.treegrid("getSetting","onExpand").apply(d)}});return c},initExpander:function(){var e=b(this);var c=e.find("td").get(e.treegrid("getSetting","treeColumn"));var d=e.treegrid("getSetting","expanderTemplate");var f=e.treegrid("getSetting","getExpander").apply(this);if(f){f.remove()}b(d).prependTo(c).click(function(){b(b(this).closest("tr")).treegrid("toggle")});return e},initIndent:function(){var e=b(this);e.find(".treegrid-indent").remove();var c=e.treegrid("getSetting","indentTemplate");var g=e.find(".treegrid-expander");var f=e.treegrid("getDepth");for(var d=0;d<f;d++){b(c).insertBefore(g)}return e},initState:function(){var c=b(this);if(c.treegrid("getSetting","saveState")&&!c.treegrid("isFirstInit")){c.treegrid("restoreState")}else{if(c.treegrid("getSetting","initialState")==="expanded"){c.treegrid("expand")}else{c.treegrid("collapse")}}return c},isFirstInit:function(){var c=b(this).treegrid("getTreeContainer");if(c.data("first_init")===undefined){c.data("first_init",b.cookie(c.treegrid("getSetting","saveStateName"))===undefined)}return c.data("first_init")},saveState:function(){var e=b(this);if(e.treegrid("getSetting","saveStateMethod")==="cookie"){var d=b.cookie(e.treegrid("getSetting","saveStateName"))||"";var c=(d===""?[]:d.split(","));var f=e.treegrid("getNodeId");if(e.treegrid("isExpanded")){if(b.inArray(f,c)===-1){c.push(f)}}else{if(e.treegrid("isCollapsed")){if(b.inArray(f,c)!==-1){c.splice(b.inArray(f,c),1)}}}b.cookie(e.treegrid("getSetting","saveStateName"),c.join(","))}return e},restoreState:function(){var d=b(this);if(d.treegrid("getSetting","saveStateMethod")==="cookie"){var c=b.cookie(d.treegrid("getSetting","saveStateName")).split(",");if(b.inArray(d.treegrid("getNodeId"),c)!==-1){d.treegrid("expand")}else{d.treegrid("collapse")}}return d},getSetting:function(c){if(!b(this).treegrid("getTreeContainer")){return null}return b(this).treegrid("getTreeContainer").data("settings")[c]},setSettings:function(c){b(this).treegrid("getTreeContainer").data("settings",c)},getTreeContainer:function(){return b(this).data("treegrid")},setTreeContainer:function(c){return b(this).data("treegrid",c)},getRootNodes:function(){return b(this).treegrid("getSetting","getRootNodes").apply(this,[b(this).treegrid("getTreeContainer")])},getAllNodes:function(){return b(this).treegrid("getSetting","getAllNodes").apply(this,[b(this).treegrid("getTreeContainer")])},isNode:function(){return b(this).treegrid("getNodeId")!==null},getNodeId:function(){if(b(this).treegrid("getSetting","getNodeId")===null){return null}else{return b(this).treegrid("getSetting","getNodeId").apply(this)}},getParentNodeId:function(){return b(this).treegrid("getSetting","getParentNodeId").apply(this)},getParentNode:function(){if(b(this).treegrid("getParentNodeId")===null){return null}else{return b(this).treegrid("getSetting","getNodeById").apply(this,[b(this).treegrid("getParentNodeId"),b(this).treegrid("getTreeContainer")])}},getChildNodes:function(){return b(this).treegrid("getSetting","getChildNodes").apply(this,[b(this).treegrid("getNodeId"),b(this).treegrid("getTreeContainer")])},getDepth:function(){if(b(this).treegrid("getParentNode")===null){return 0}return b(this).treegrid("getParentNode").treegrid("getDepth")+1},isRoot:function(){return b(this).treegrid("getDepth")===0},isLeaf:function(){return b(this).treegrid("getChildNodes").length===0},isLast:function(){if(b(this).treegrid("isNode")){var c=b(this).treegrid("getParentNode");if(c===null){if(b(this).treegrid("getNodeId")===b(this).treegrid("getRootNodes").last().treegrid("getNodeId")){return true}}else{if(b(this).treegrid("getNodeId")===c.treegrid("getChildNodes").last().treegrid("getNodeId")){return true}}}return false},isFirst:function(){if(b(this).treegrid("isNode")){var c=b(this).treegrid("getParentNode");if(c===null){if(b(this).treegrid("getNodeId")===b(this).treegrid("getRootNodes").first().treegrid("getNodeId")){return true}}else{if(b(this).treegrid("getNodeId")===c.treegrid("getChildNodes").first().treegrid("getNodeId")){return true}}}return false},isExpanded:function(){return b(this).hasClass("treegrid-expanded")},isCollapsed:function(){return b(this).hasClass("treegrid-collapsed")},isOneOfParentsCollapsed:function(){var c=b(this);if(c.treegrid("isRoot")){return false}else{if(c.treegrid("getParentNode").treegrid("isCollapsed")){return true}else{return c.treegrid("getParentNode").treegrid("isOneOfParentsCollapsed")}}},expand:function(){if(!this.treegrid("isLeaf")&&!this.treegrid("isExpanded")){this.trigger("expand");this.trigger("change");return this}return this},expandAll:function(){var c=b(this);c.treegrid("getRootNodes").treegrid("expandRecursive");return c},expandRecursive:function(){return b(this).each(function(){var c=b(this);c.treegrid("expand");if(!c.treegrid("isLeaf")){c.treegrid("getChildNodes").treegrid("expandRecursive")}})},collapse:function(){return b(this).each(function(){var c=b(this);if(!c.treegrid("isLeaf")&&!c.treegrid("isCollapsed")){c.trigger("collapse");c.trigger("change")}})},collapseAll:function(){var c=b(this);c.treegrid("getRootNodes").treegrid("collapseRecursive");return c},collapseRecursive:function(){return b(this).each(function(){var c=b(this);c.treegrid("collapse");if(!c.treegrid("isLeaf")){c.treegrid("getChildNodes").treegrid("collapseRecursive")}})},toggle:function(){var c=b(this);if(c.treegrid("isExpanded")){c.treegrid("collapse")}else{c.treegrid("expand")}return c},render:function(){return b(this).each(function(){var c=b(this);if(c.treegrid("isOneOfParentsCollapsed")){c.hide()}else{c.show()}if(!c.treegrid("isLeaf")){c.treegrid("renderExpander");c.treegrid("getChildNodes").treegrid("render")}})},renderExpander:function(){return b(this).each(function(){var c=b(this);var d=c.treegrid("getSetting","getExpander").apply(this);if(d){if(!c.treegrid("isCollapsed")){d.removeClass(c.treegrid("getSetting","expanderCollapsedClass"));d.addClass(c.treegrid("getSetting","expanderExpandedClass"))}else{d.removeClass(c.treegrid("getSetting","expanderExpandedClass"));d.addClass(c.treegrid("getSetting","expanderCollapsedClass"))}}else{c.treegrid("initExpander");c.treegrid("renderExpander")}})}};b.fn.treegrid=function(c){if(a[c]){return a[c].apply(this,Array.prototype.slice.call(arguments,1))}else{if(typeof c==="object"||!c){return a.initTree.apply(this,arguments)}else{b.error("Method with name "+c+" does not exists for jQuery.treegrid")}}};b.fn.treegrid.defaults={initialState:"expanded",saveState:false,saveStateMethod:"cookie",saveStateName:"tree-grid-state",expanderTemplate:'<span class="treegrid-expander"></span>',indentTemplate:'<span class="treegrid-indent"></span>',expanderExpandedClass:"treegrid-expander-expanded",expanderCollapsedClass:"treegrid-expander-collapsed",treeColumn:0,getExpander:function(){return b(this).find(".treegrid-expander")},getNodeId:function(){var c=/treegrid-([A-Za-z0-9_-]+)/;if(c.test(b(this).attr("class"))){return c.exec(b(this).attr("class"))[1]}return null},getParentNodeId:function(){var c=/treegrid-parent-([A-Za-z0-9_-]+)/;if(c.test(b(this).attr("class"))){return c.exec(b(this).attr("class"))[1]}return null},getNodeById:function(e,c){var d="treegrid-"+e;return c.find("tr."+d)},getChildNodes:function(e,c){var d="treegrid-parent-"+e;return c.find("tr."+d)},getTreeGridContainer:function(){return b(this).closest("table")},getRootNodes:function(d){var c=b.grep(d.find("tr"),function(f){var g=b(f).attr("class");var h=/treegrid-([A-Za-z0-9_-]+)/;var e=/treegrid-parent-([A-Za-z0-9_-]+)/;return h.test(g)&&!e.test(g)});return b(c)},getAllNodes:function(d){var c=b.grep(d.find("tr"),function(e){var f=b(e).attr("class");var g=/treegrid-([A-Za-z0-9_-]+)/;return g.test(f)});return b(c)},onCollapse:null,onExpand:null,onChange:null}})(jQuery);