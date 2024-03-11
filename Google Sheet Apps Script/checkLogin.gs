	//here goes your spreadSheet url - replace it
 var spreadSheet = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1LI98zYG0FemPXymJ5HekbisYmdvqbbaRvnglbPnK3SQ/edit#gid=612592268");
	//here goes your spreadSheet sheet name - replace it
 var sheet = spreadSheet.getSheetByName("Sheet2");

function doGet(e) {

 var action = e.parameter.action;

 if(action == "login"){
  return checkData(e);
 }
  
}

function checkLogin(username, password) {

  // Get the data range in the sheet
  var data = sheet.getDataRange().getValues();
 

  // Loop through each row to check for a match
  for (var i = 0; i < data.length; i++) {
    var sheetUsername = data[i][0];
    var sheetPassword = data[i][1];

    // Check if the username and password match
    if (sheetUsername === username && sheetPassword === password) {
      
      ContentService.createTextOutput("Провера података у току.").setMimeType(ContentService.MimeType.TEXT);
      return true; // User can log in
    } 
  }

  return false; // User cannot log in

}

function checkData(e){
 
 
  var username = e.parameter.username;
  var password = e.parameter.password;
  var cell = sheet.getRange("E5"); 
  if(checkLogin(username, password)===true){
    cell.clearContent();
    cell.setValue("postoji");
  } else{
    cell.clearContent();
    cell.setValue("nepostoji");
  }

   
}

