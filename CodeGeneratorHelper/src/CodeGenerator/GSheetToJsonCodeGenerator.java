/*
 * This project create PHP raw code to generate JSON data set from Google sheet 
 * Google sheet link: https://spreadsheets.google.com/feeds/list/<sheetKey>/1/public/values?alt=json
 * SheetKey is public type shareable key (1H8-0GDDwzEMeuT3wHDXKQ8wvSKdUCe1V7UTXaGpLh5w)
 * 
 * and open the template in the editor.
 */
package CodeGenerator;

/**
 *
 * @author emran
 */
public class GSheetToJsonCodeGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GSheetToJsonCodeGenerator helper = new GSheetToJsonCodeGenerator();

        String val = "isDontShow	departmentId	teachersID	teachersName	idNo	eduQualifications	departmentName	teacherPost	collegeName	teachersEmail	teachersPhone	socialLink	imageUrls";
        val = val.replace(" ", val);
        helper.sheetColumnVal2PhpParse("teachersInfo",val);
        
        //"departmentId", "teachersID", "teachersName", "idNo", "eduQualifications", "teacherPost", "iconUrl", "collegeName","teachersEmail", "teachersPhone", "socialLink", "imageUrls"
        //val = "isDontShow\",\"sldId\",\"title\",\"subTitle\",\"imgUrl\",\"webUrl\",\"youtubeUrl\",\"nextLevelId";
        val = helper.removeTabAddComa(val);
        
        helper.removeDoubleCotetion(val);

//        String valc = "b,a,a4e3da,56b3a7,e47852,fda88b,6e8ec8,9ebef1,f69fd6,cf5aa6,5251da,8786fb,f88c8c,db5d5e,8cdbf9,4ca6c8,bca1f2,855dd9,8cd5ca,40ac9b,997fbc,725398,a0d69a,5ca553,ffbb94,e38466";
//        helper.filterEventString(valc);
    }
    
    private String removeTabAddComa(String val){
        if(val==null){
            return null;
        }
        val = val.replace("	", "\",\"");
        System.out.println(""+val);
        return val;
    }
    
    private String filterEventString(String valc){
        
        String[] spltValc = valc.split(",");
        String colorA = "", colorB="";
        String coma = ",";
        int numberOfA = 0, numberOfB = 0;
        for(int i= 0; i<spltValc.length; i++){
            spltValc[i]="0xE8"+spltValc[i];
            if((i+1)==spltValc.length){
                coma = "";
            }
            if((i%2)!=0){
                colorA = colorA+spltValc[i]+coma;
                ++numberOfA;
            }
            else{
                colorB = colorB+spltValc[i]+",";
                ++numberOfB;
            }
        }
        
        System.out.println("colorA: "+colorA+ " - - "+numberOfA);
        System.out.println("colorB: "+colorB+ " - - "+numberOfB);
               
        
        return null;
    }

    private void sheetColumnVal2PhpParse(String jsonIndexName,String val) {

        val = val.replace("	", ",");
        
        String[] columansName = val.split(",");

        StringBuilder startTag = new StringBuilder();
        startTag.append("foreach($entrys as $singleEntry){\n"
                + "     $rowMetadata[] =	array(\n");

        String coma = ",";
        for (int i = 0; i < columansName.length; i++) {
            if ((i + 1) == columansName.length) {
                coma = "\n);";
            }
            startTag.append("\t\t\t'" + columansName[i] + "' => $singleEntry['gsx$" + columansName[i].toLowerCase() + "']['$t']" + coma + "\n");
        }//end for
        startTag.append("}\n"
                + "$post = array(\n"
                + "			'"+jsonIndexName+"'    =>  $rowMetadata\n"
                + ");\n"
                + "echo json_encode($post);");

        System.out.println("" + startTag.toString());
    }//end function
    
    private void removeDoubleCotetion(String val){
        val = val.replace("\"", "");
        System.out.println("" +val);
    }
}//end class

/*
Demo java code:
<?php

/*
//create date: 20190428
//creator developer: emran
//update version: 0.1
//last update date: 201404281927 //Year Month Date Hour Minutes
//updator: emran_v0_20190428

//ACCEPTED APP ID WILL BE 1
if (!isset($_REQUEST['appId'])) {
    echo "invalid_request";
    return;
}

$appId=$_REQUEST['appId'];

$url = 'https://spreadsheets.google.com/feeds/list/1H8-0GDDwzSGeuT3wHDXKQ8wvSKdUCe1V7UTXaGpLh5w/7/public/values?alt=json';
// 7 is tab number of sheet

//$data=params
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLINFO_HEADER_OUT, true);
curl_setopt($ch, CURLOPT_GET, true);
//curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
 
// Set HTTP Header for POST request 
// curl_setopt($ch, CURLOPT_HTTPHEADER, array(
//     'Content-Type: application/x-www-form-urlencoded',
//     'Authorization: Bearer '.$token)
// );
 
$result = curl_exec($ch);
curl_close($ch);
$data = json_decode($result, true);


if(!isset($data)){
    //TODO, show message
    echo "$data is not set";
    return;
}
    
    $entrys= $data['feed']['entry'];
	$tempEntrys[] =	array(
	    	'data3' => $entrys
	    );
    
//Generated gode
foreach($entrys as $singleEntry){
     $rowMetadata[] =	array(
			'isDontShow' => $singleEntry['gsx$isdontshow']['$t'],
			'departmentId' => $singleEntry['gsx$departmentid']['$t'],
			'menuId' => $singleEntry['gsx$menuid']['$t'],
			'sub1Id' => $singleEntry['gsx$sub1id']['$t'],
			'title' => $singleEntry['gsx$title']['$t'],
			'subTitle' => $singleEntry['gsx$subtitle']['$t'],
			'iconUrl' => $singleEntry['gsx$iconurl']['$t'],
			'webUrl' => $singleEntry['gsx$weburl']['$t'],
			'youtubeUrl' => $singleEntry['gsx$youtubeurl']['$t'],
			'nextLevelId' => $singleEntry['gsx$nextlevelid']['$t'],
			'insertDateTime' => $singleEntry['gsx$insertdatetime']['$t'],
			'updateDateTime' => $singleEntry['gsx$updatedatetime']['$t']
);
}
$post = array(
			'sub1s'    =>  $rowMetadata
);
echo json_encode($post);
//---------------------------------

return;
//----0---

?>
*/