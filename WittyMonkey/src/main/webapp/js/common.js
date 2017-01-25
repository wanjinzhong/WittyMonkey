/**
 * 国际化
 */
$(document).ready(function(){
	loadProperties("messages", "resources/i18n/");
});
function loadProperties(name, path, lang){
	var lang = lang || navigator.language;
	jQuery.i18n.properties({
		name: name,
		path: path,
		mode: 'map',
		language: lang
	})
}
