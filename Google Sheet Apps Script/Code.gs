	//here goes your spreadSheet url - replace it
 var spreadSheet = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1LI98zYG0FemPXymJ5HekbisYmdvqbbaRvnglbPnK3SQ/edit#gid=612592268");
	//here goes your spreadSheet sheet name - replace it
 var sheet = spreadSheet.getSheetByName("Sheet2");


function doGet(e) {

 var action = e.parameter.action;

 if(action == "create"){
  return createData(e);
 }
  
}

function createData(e){
 
  var id = sheet.getLastRow()+".";
  var jedan = e.parameter.jedan;
  var dva = e.parameter.dva;
  var tri = e.parameter.tri;
  var cetiri = e.parameter.cetiri;
  var pet = e.parameter.pet;
  var sest = e.parameter.sest;
    var currentDate = new Date(); // Get the current date and time

  sheet.appendRow([id,jedan,dva,tri,cetiri,pet,sest,currentDate]);



  return ContentService.createTextOutput("Успешно сте унели податке.").setMimeType(ContentService.MimeType.TEXT);
}
