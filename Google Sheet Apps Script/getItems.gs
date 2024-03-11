	//here goes your spreadSheet url - replace it
 var spreadSheet = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1LI98zYG0FemPXymJ5HekbisYmdvqbbaRvnglbPnK3SQ/edit#gid=612592268");
	//here goes your spreadSheet sheet name - replace it
 var sheet = spreadSheet.getSheetByName("Sheet2");

function doPost(e){
  var action = e.parameter.action;
  if(action == 'addItem'){
    return addItem(e);
  }
}


function doGet(e){
  var action = e.parameter.action;
  if(action == 'getItems'){
    return getItems(e);
  }
}

function addItem(e){
  var text = "Item" + sheet.getLastRow();
  var text1 = e.parameter.text;
  var text2 = e.parameter.text2;
  var text3 = e.parameter.text3;
  var text4 = e.parameter.text4;
  var text5 = e.parameter.text5;
  var text6 = e.parameter.text6;
  sheet.appendRow([text,text1,text2,text3,text4,text5,text6]);
  return ContentService.createTextOutput("Success").setMimType(ContentService.MimeType.TEXT);
}

function getItems(e) {

  var records = {};
  var rows = sheet.getRange(2,1,sheet.getLastRow()-1,sheet.getLastColumn()).getValues();
  var data = [];


 for(var r=0;r<rows.length;r++){
  var row = rows[r],
  record = {};
  record['Rb.'] = row[0];
  record['Ime'] = row[1];
  record['Prezime'] = row[2];
  record['Opstina/Grad'] = row[3];
  record['Radno mesto'] = row[4];
  record['Broj telefona'] = row[5];
  record['Mejl adresa'] = row[6];
  data.push(record);
 }

  records.items = data;
  
 
    var result = JSON.stringify(records);
    return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
}
