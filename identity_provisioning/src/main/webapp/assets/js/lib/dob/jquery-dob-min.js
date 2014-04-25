(function($){"use strict";$.fn.dob=function(options){function addLeadingZero(value){return value=parseInt(value,10),10>value&&(value="0"+(""+value)),value}function removeLeadingZero(value){return value=parseInt(value,10)}function getYearFromSelect(element){var dobYear=element.find(settings.dobYear),value=dobYear.val();return value}function getDayFromSelect(element){var dobDay=element.find(settings.dobDay),value=dobDay.val();return value}function getMonthFromSelect(element){var dobMonth=element.find(settings.dobMonth),value=dobMonth.val();return value}function getDateOfBirth(element){var dobMonth,dobDay,dobYear,formatted;return dobMonth=getMonthFromSelect(element),dobDay=getDayFromSelect(element),dobYear=getYearFromSelect(element),formatted=dobMonth+"/"+dobDay+"/"+dobYear}function buildDays(element){var dobDay,month,year,days,day,template,value;dobDay=element.find(settings.dobDay),month=removeLeadingZero(getMonthFromSelect(element)),year=getYearFromSelect(element),days=new Date(year,month,0).getDate(),day=getDayFromSelect(element)||dateObj.day,day=removeLeadingZero(day),template="",value=0;for(var i=1;days>=i;i++)value=addLeadingZero(i),template=template+'<option value="'+value+'">'+value+"</option>";dobDay.html(template),day>=days&&(day=days),day=addLeadingZero(day),dobDay.val(day),dobDay.css({visibility:"visible"})}function buildYears(element){for(var dobYear=element.find(settings.dobYear),date=new Date,yearEnd=date.getFullYear()-1,yearStart=yearEnd-100,template="",i=yearStart;yearEnd>=i;i++)template=template+'<option value="'+i+'">'+i+"</option>";dobYear.html(template),dobYear.val(dateObj.year),dobYear.css({visibility:"visible"})}function buildMonths(element){var months,dobMonth,template,value;months=["Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sept.","Oct.","Nov.","Dec."],dobMonth=element.find(settings.dobMonth),template="",value=0,_.each(months,_.bind(function(month,idx){value=addLeadingZero(idx+1),template=template+'<option value="'+value+'">'+month+"</option>"})),dobMonth.html(template),dobMonth.val(dateObj.month),dobMonth.css({visibility:"visible"})}function buildDate(element){var dob,date,month,year,day;dob=element.find(settings.dob),_.isEmpty(dob.val())?(date=new Date,month=date.getMonth()+1,year=date.getFullYear()-1,day=date.getDate()):(date=dob.val().split("/"),month=date[0],day=date[1],year=date[2]),dateObj.month=addLeadingZero(month),dateObj.day=addLeadingZero(day),dateObj.year=year}function buildSelects(element){var dob=element.find(settings.dob),loader=element.find(settings.dobLoader);buildDate(element),buildMonths(element),buildYears(element),buildDays(element),dob.val(getDateOfBirth(element)),loader.fadeOut()}function initializeListeners(element){var dob=element.find(settings.dob),control=element,select=control.find("select");select.on("change",_.bind(function(evt){var targetId="#"+$(evt.target).attr("id");targetId!==settings.dobDay&&buildDays(element),dob.val(getDateOfBirth(element))},this))}var dateObj,settings;return dateObj={month:void 0,day:void 0,year:void 0},settings=$.extend({dobMonth:"#dobMonth",dobDay:"#dobDay",dobYear:"#dobYear",dobLoader:"#dobLoader",dob:"#dob"},options),this.each(function(idx,elem){var element=$(elem).addClass("dob");buildSelects(element),initializeListeners(element)})}})(jQuery);