(function(a){"object"==typeof exports&&"object"==typeof module?a(require("../../lib/codemirror")):"function"==typeof define&&define.amd?define(["../../lib/codemirror"],a):a(CodeMirror)})(function(a){a.defineMode("javascript",function(ax,ak){function am(b,g,f){aZ=b;aU=f;return g}function ah(g,m){var k=g.next();if('"'==k||"'"==k){return m.tokenize=ab(k),m.tokenize(g,m)}if("."==k&&g.match(/^\d+(?:[eE][+\-]?\d+)?/)){return am("number","number")}if("."==k&&g.match("..")){return am("spread","meta")}if(/[\[\]{}\(\),;\:\.]/.test(k)){return am(k)}if("\x3d"==k&&g.eat("\x3e")){return am("\x3d\x3e","operator")}if("0"==k&&g.eat(/x/i)){return g.eatWhile(/[\da-f]/i),am("number","number")}if(/\d/.test(k)){return g.match(/^\d*(?:\.\d*)?(?:[eE][+\-]?\d+)?/),am("number","number")}if("/"==k){if(g.eat("*")){return m.tokenize=aS,aS(g,m)}if(g.eat("/")){return g.skipToEnd(),am("comment","comment")}if("operator"==m.lastType||"keyword c"==m.lastType||"sof"==m.lastType||/^[\[{}\(,;:]$/.test(m.lastType)){g:for(var k=!1,l,f=!1;null!=(l=g.next());){if(!k){if("/"==l&&!f){break g}"["==l?f=!0:f&&"]"==l&&(f=!1)}k=!k&&"\\"==l}g.match(/^\b(([gimyu])(?![gimyu]*\2))+\b/);return am("regexp","string-2")}g.eatWhile(aR);return am("operator","operator",g.current())}if("`"==k){return m.tokenize=aK,aK(g,m)}if("#"==k){return g.skipToEnd(),am("error","error")}if(aR.test(k)){return g.eatWhile(aR),am("operator","operator",g.current())}if(aJ.test(k)){return g.eatWhile(aJ),k=g.current(),(l=aM.propertyIsEnumerable(k)&&aM[k])&&"."!=m.lastType?am(l.type,l.style,k):am("variable","variable",k)}}function ab(b){return function(l,g){var k=!1,f;if(aQ&&"@"==l.peek()&&l.match(i)){return g.tokenize=ah,am("jsonld-keyword","meta")}for(;null!=(f=l.next())&&(f!=b||k);){k=!k&&"\\"==f}k||(g.tokenize=ah);return am("string","string")}}function aS(b,k){for(var f=!1,g;g=b.next();){if("/"==g&&f){k.tokenize=ah;break}f="*"==g}return am("comment","comment")}function aK(b,k){for(var f=!1,g;null!=(g=b.next());){if(!f&&("`"==g||"$"==g&&b.eat("{"))){k.tokenize=ah;break}f=!f&&"\\"==g}return am("quasi","string-2",b.current())}function aI(l,t){t.fatArrowAt&&(t.fatArrowAt=null);var q=l.string.indexOf("\x3d\x3e",l.start);if(!(0>q)){for(var r=0,k=!1,q=q-1;0<=q;--q){var n=l.string.charAt(q),m="([{}])".indexOf(n);if(0<=m&&3>m){if(!r){++q;break}if(0==--r){break}}else{if(3<=m&&6>m){++r}else{if(aJ.test(n)){k=!0}else{if(/["'\/]/.test(n)){return}if(k&&!r){++q;break}}}}}k&&!r&&(t.fatArrowAt=q)}}function ao(k,q,g,n,m,l){this.indented=k;this.column=q;this.type=g;this.prev=m;this.info=l;null!=n&&(this.align=n)}function av(){for(var b=arguments.length-1;0<=b;b--){aw.cc.push(arguments[b])}}function az(){av.apply(null,arguments);return !0}function ag(g){function k(b){for(;b;b=b.next){if(b.name==g){return !0}}return !1}var f=aw.state;f.context?(aw.marked="def",k(f.localVars)||(f.localVars={name:g,next:f.localVars})):!k(f.globalVars)&&ak.globalVars&&(f.globalVars={name:g,next:f.globalVars})}function af(){aw.state.context={prev:aw.state.context,vars:aw.state.localVars};aw.state.localVars=bk}function ae(){aw.state.localVars=aw.state.context.vars;aw.state.context=aw.state.context.prev}function aq(g,k){var f=function(){var l=aw.state,n=l.indented;if("stat"==l.lexical.type){n=l.lexical.indented}else{for(var m=l.lexical;m&&")"==m.type&&m.align;m=m.prev){n=m.indented}}l.lexical=new ao(n,aw.stream.column(),g,null,l.lexical,k)};f.lex=!0;return f}function ar(){var b=aw.state;b.lexical.prev&&(")"==b.lexical.type&&(b.indented=b.lexical.indented),b.lexical=b.lexical.prev)}function ap(b){function f(g){return g==b?az():";"==b?av():az(f)}return f}function al(b,f){return"var"==b?az(aq("vardef",f.length),aH,ap(";"),ar):"keyword a"==b?az(aq("form"),an,al,ar):"keyword b"==b?az(aq("form"),al,ar):"{"==b?az(aq("}"),aG,ar):";"==b?az():"if"==b?("else"==aw.state.lexical.info&&aw.state.cc[aw.state.cc.length-1]==ar&&aw.state.cc.pop()(),az(aq("form"),an,al,ar,o)):"function"==b?az(ai):"for"==b?az(aq("form"),d,al,ar):"variable"==b?az(aq("stat"),be):"switch"==b?az(aq("form"),an,aq("}","switch"),ap("{"),aG,ar,ar):"case"==b?az(an,ap(":")):"default"==b?az(ap(":")):"catch"==b?az(aq("form"),af,ap("("),aF,ap(")"),al,ar,ae):"module"==b?az(aq("form"),af,a6,ae,ar):"class"==b?az(aq("form"),aA,ar):"export"==b?az(aq("form"),ad,ar):"import"==b?az(aq("form"),j,ar):av(aq("stat"),an,ap(";"),ar)}function an(b){return bg(b,!1)}function aj(b){return bg(b,!0)}function bg(b,g){if(aw.state.fatArrowAt==aw.stream.start){var f=g?a9:aT;if("("==b){return az(af,aq(")"),aY(a5,")"),ar,ap("\x3d\x3e"),f,ae)}if("variable"==b){return av(af,a5,ap("\x3d\x3e"),f,ae)}}f=g?aE:aP;return c.hasOwnProperty(b)?az(f):"function"==b?az(ai,f):"keyword c"==b?az(g?at:aD):"("==b?az(aq(")"),aD,aO,ap(")"),ar,f):"operator"==b||"spread"==b?az(g?aj:an):"["==b?az(aq("]"),bf,ar,f):"{"==b?aX(a7,"}",null,f):"quasi"==b?av(aN,f):az()}function aD(b){return b.match(/[;\}\)\],]/)?av():av(an)}function at(b){return b.match(/[;\}\)\],]/)?av():av(aj)}function aP(b,f){return","==b?az(an):aE(b,f,!1)}function aE(b,m,k){var l=0==k?aP:aE,g=0==k?an:aj;if("\x3d\x3e"==b){return az(af,k?a9:aT,ae)}if("operator"==b){return/\+\+|--/.test(m)?az(l):"?"==m?az(an,ap(":"),g):az(g)}if("quasi"==b){return av(aN,l)}if(";"!=b){if("("==b){return aX(aj,")","call",l)}if("."==b){return az(bh,l)}if("["==b){return az(aq("]"),aD,ap("]"),ar,l)}}}function aN(b,f){return"quasi"!=b?av():"${"!=f.slice(f.length-2)?az(aN):az(an,bb)}function bb(b){if("}"==b){return aw.marked="string-2",aw.state.tokenize=aK,az(aN)}}function aT(b){aI(aw.stream,aw.state);return av("{"==b?al:an)}function a9(b){aI(aw.stream,aw.state);return av("{"==b?al:aj)}function be(b){return":"==b?az(ar,al):av(aP,ap(";"),ar)}function bh(b){if("variable"==b){return aw.marked="property",az()}}function a7(b,f){if("variable"==b||"keyword"==aw.style){return aw.marked="property","get"==f||"set"==f?az(aV):az(aW)}if("number"==b||"string"==b){return aw.marked=aQ?"property":aw.style+" property",az(aW)}if("jsonld-keyword"==b){return az(aW)}if("["==b){return az(an,ap("]"),aW)}}function aV(b){if("variable"!=b){return av(aW)}aw.marked="property";return az(ai)}function aW(b){if(":"==b){return az(aj)}if("("==b){return av(ai)}}function aY(b,g){function f(k){return","==k?(k=aw.state.lexical,"call"==k.info&&(k.pos=(k.pos||0)+1),az(b,f)):k==g?az():az(ap(g))}return function(k){return k==g?az():av(b,f)}}function aX(b,k,f){for(var g=3;g<arguments.length;g++){aw.cc.push(arguments[g])}return az(aq(k,f),aY(b,k),ar)}function aG(b){return"}"==b?az():av(al,aG)}function p(b){if(e&&":"==b){return az(au)}}function au(b){if("variable"==b){return aw.marked="variable-3",az()}}function aH(){return av(a5,p,aC,s)}function a5(b,f){if("variable"==b){return ag(f),az()}if("["==b){return aX(a5,"]")}if("{"==b){return aX(h,"}")}}function h(b,f){if("variable"==b&&!aw.stream.match(/^\s*:/,!1)){return ag(f),az(aC)}"variable"==b&&(aw.marked="property");return az(ap(":"),a5,aC)}function aC(b,f){if("\x3d"==f){return az(aj)}}function s(b){if(","==b){return az(aH)}}function o(b,f){if("keyword b"==b&&"else"==f){return az(aq("form","else"),al,ar)}}function d(b){if("("==b){return az(aq(")"),bj,ap(")"),ar)}}function bj(b){return"var"==b?az(aH,ap(";"),aL):";"==b?az(aL):"variable"==b?az(bd):av(an,ap(";"),aL)}function bd(b,f){return"in"==f||"of"==f?(aw.marked="keyword",az(an)):az(aP,aL)}function aL(b,f){return";"==b?az(bi):"in"==f||"of"==f?(aw.marked="keyword",az(an)):av(an,ap(";"),bi)}function bi(b){")"!=b&&az(an)}function ai(b,f){if("*"==f){return aw.marked="keyword",az(ai)}if("variable"==b){return ag(f),az(ai)}if("("==b){return az(af,aq(")"),aY(aF,")"),ar,al,ae)}}function aF(b){return"spread"==b?az(aF):av(a5,p)}function aA(b,f){if("variable"==b){return ag(f),az(bc)}}function bc(b,f){if("extends"==f){return az(an,bc)}if("{"==b){return az(aq("}"),a4,ar)}}function a4(b,f){if("variable"==b||"keyword"==aw.style){if("static"==f){return aw.marked="keyword",az(a4)}aw.marked="property";return"get"==f||"set"==f?az(a2,ai,a4):az(ai,a4)}if("*"==f){return aw.marked="keyword",az(a4)}if(";"==b){return az(a4)}if("}"==b){return az()}}function a2(b){if("variable"!=b){return av()}aw.marked="property";return az()}function a6(b,f){if("string"==b){return az(al)}if("variable"==b){return ag(f),az(aB)}}function ad(b,f){return"*"==f?(aw.marked="keyword",az(aB,ap(";"))):"default"==f?(aw.marked="keyword",az(an,ap(";"))):av(al)}function j(b){return"string"==b?az():av(a8,aB)}function a8(b,f){if("{"==b){return aX(a8,"}")}"variable"==b&&ag(f);"*"==f&&(aw.marked="keyword");return az(ay)}function ay(b,f){if("as"==f){return aw.marked="keyword",az(a8)}}function aB(b,f){if("from"==f){return aw.marked="keyword",az(an)}}function bf(b){return"]"==b?az():av(aj,ac)}function ac(b){return"for"==b?av(aO,ap("]")):","==b?az(aY(at,"]")):av(aY(aj,"]"))}function aO(b){if("for"==b){return az(d,aO)}if("if"==b){return az(an,aO)}}var a3=ax.indentUnit,a0=ak.statementIndent,aQ=ak.jsonld,a1=ak.json||aQ,e=ak.typescript,aJ=ak.wordCharacters||/[\w$\xa1-\uffff]/,aM=function(){function l(b){return{type:b,style:"keyword"}}var t=l("keyword a"),k=l("keyword b"),r=l("keyword c"),q=l("operator"),m={type:"atom",style:"atom"},t={"if":l("if"),"while":t,"with":t,"else":k,"do":k,"try":k,"finally":k,"return":r,"break":r,"continue":r,"new":r,"delete":r,"throw":r,"debugger":r,"var":l("var"),"const":l("var"),let:l("var"),"function":l("function"),"catch":l("catch"),"for":l("for"),"switch":l("switch"),"case":l("case"),"default":l("default"),"in":q,"typeof":q,"instanceof":q,"true":m,"false":m,"null":m,undefined:m,NaN:m,Infinity:m,"this":l("this"),module:l("module"),"class":l("class"),"super":l("atom"),yield:r,"export":l("export"),"import":l("import"),"extends":r};if(e){var k={type:"variable",style:"variable-3"},k={"interface":l("interface"),"extends":l("extends"),constructor:l("constructor"),"public":l("public"),"private":l("private"),"protected":l("protected"),"static":l("static"),string:k,number:k,bool:k,any:k},n;for(n in k){t[n]=k[n]}}return t}(),aR=/[+\-*&%=<>!?|~^]/,i=/^@(context|id|value|language|type|container|list|set|reverse|index|base|vocab|graph)"/,aZ,aU,c={atom:!0,number:!0,variable:!0,string:!0,regexp:!0,"this":!0,"jsonld-keyword":!0},aw={state:null,column:null,marked:null,cc:null},bk={name:"this",next:{name:"arguments"}};ar.lex=!0;return{startState:function(b){b={tokenize:ah,lastType:"sof",cc:[],lexical:new ao((b||0)-a3,0,"block",!1),localVars:ak.localVars,context:ak.localVars&&{vars:ak.localVars},indented:0};ak.globalVars&&"object"==typeof ak.globalVars&&(b.globalVars=ak.globalVars);return b},token:function(k,f){k.sol()&&(f.lexical.hasOwnProperty("align")||(f.lexical.align=!1),f.indented=k.indentation(),aI(k,f));if(f.tokenize!=aS&&k.eatSpace()){return null}var n=f.tokenize(k,f);if("comment"==aZ){return n}f.lastType="operator"!=aZ||"++"!=aU&&"--"!=aU?aZ:"incdec";k:{var q=aZ,m=aU,l=f.cc;aw.state=f;aw.stream=k;aw.marked=null;aw.cc=l;aw.style=n;f.lexical.hasOwnProperty("align")||(f.lexical.align=!0);for(;;){if((l.length?l.pop():a1?an:al)(q,m)){for(;l.length&&l[l.length-1].lex;){l.pop()()}if(aw.marked){n=aw.marked;break k}if(q="variable"==q){f:{for(q=f.localVars;q;q=q.next){if(q.name==m){q=!0;break f}}for(l=f.context;l;l=l.prev){for(q=l.vars;q;q=q.next){if(q.name==m){q=!0;break f}}}q=void 0}}if(q){n="variable-2";break k}break k}}}return n},indent:function(l,k){if(l.tokenize==aS){return a.Pass}if(l.tokenize!=ah){return 0}var q=k&&k.charAt(0),r=l.lexical;if(!/^\s*else\b/.test(k)){for(var n=l.cc.length-1;0<=n;--n){var m=l.cc[n];if(m==ar){r=r.prev}else{if(m!=o){break}}}}"stat"==r.type&&"}"==q&&(r=r.prev);a0&&")"==r.type&&"stat"==r.prev.type&&(r=r.prev);n=r.type;m=q==n;return"vardef"==n?r.indented+("operator"==l.lastType||","==l.lastType?r.info+1:0):"form"==n&&"{"==q?r.indented:"form"==n?r.indented+a3:"stat"==n?(q=r.indented,r="operator"==l.lastType||","==l.lastType||aR.test(k.charAt(0))||/[,.]/.test(k.charAt(0)),q+(r?a0||a3:0)):"switch"!=r.info||m||0==ak.doubleIndentSwitch?r.align?r.column+(m?0:1):r.indented+(m?0:a3):r.indented+(/^(?:case|default)\b/.test(k)?a3:2*a3)},electricInput:/^\s*(?:case .*?:|default:|\{|\})$/,blockCommentStart:a1?null:"/*",blockCommentEnd:a1?null:"*/",lineComment:a1?null:"//",fold:"brace",closeBrackets:"()[]{}''\"\"``",helperType:a1?"json":"javascript",jsonldMode:aQ,jsonMode:a1}});a.registerHelper("wordChars","javascript",/[\w$]/);a.defineMIME("text/javascript","javascript");a.defineMIME("text/ecmascript","javascript");a.defineMIME("application/javascript","javascript");a.defineMIME("application/x-javascript","javascript");a.defineMIME("application/ecmascript","javascript");a.defineMIME("application/json",{name:"javascript",json:!0});a.defineMIME("application/x-json",{name:"javascript",json:!0});a.defineMIME("application/ld+json",{name:"javascript",jsonld:!0});a.defineMIME("text/typescript",{name:"javascript",typescript:!0});a.defineMIME("application/typescript",{name:"javascript",typescript:!0})});