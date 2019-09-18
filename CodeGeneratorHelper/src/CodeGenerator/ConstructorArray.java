/*
 * This class help to create Array Constructor from fildname
 * Like
public TeacherInfoModel(String[] valueSet){
	switch (valueSet.length) {
	case 13: this.mImageUrls = valueSet[12];
	case 12: this.mSocialLink = valueSet[11];
	case 11: this.mTeachersPhone = valueSet[10];
	case 10: this.mTeachersEmail = valueSet[9];
	case 9: this.mCollegeName = valueSet[8];
	case 8: this.mTeacherPost = valueSet[7];
	case 7: this.mDepartmentName = valueSet[6];
	case 6: this.mEduQualifications = valueSet[5];
	case 5: this.mIdNo = valueSet[4];
	case 4: this.mTeachersName = valueSet[3];
	case 3: this.mTeachersID = valueSet[2];
	case 2: this.mDepartmentId = valueSet[1];
	case 1: this.mIsDontShow = valueSet[0];
	default: { }
}
}
 */
package CodeGenerator;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author emran
 */
public class ConstructorArray {
  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        System.out.println("dateY: "+dateY+", dateM: "+dateM+", dateD: "+dateD);
        ConstructorArray application7 = new ConstructorArray();

        String inputValue = "mIsDontShow, mDepartmentId, mTeachersID, mTeachersName, mIdNo, mEduQualifications,mDepartmentName, mTeacherPost, mCollegeName, mTeachersEmail, mTeachersPhone, mSocialLink, mImageUrls";
        inputValue = inputValue.replace("\"", "");
        System.out.println(application7.arrayConstructorCreator("TeacherInfoModel", inputValue));
    }

    public String arrayConstructorCreator(String constructName, String inputValue) {

        if (inputValue == null) {
            return null;
        }
        String splitvalue = ",";
        List<String> StringList = null;

        try {
            StringList = Arrays.asList(inputValue.split("\\s*" + splitvalue + "\\s*"));
            int dataSize = StringList.size();
            constructName = "\npublic " + constructName + "(String[] valueSet){\n\tswitch (valueSet.length) {\n\t";
            for (int i = 0; i < dataSize; i++) {
                constructName += "case " + (dataSize - i) + ": this." + StringList.get((dataSize - (1 + i))) + " = valueSet[" + (dataSize - (1 + i)) + "];\n\t";
            }
            constructName += "default: { }\n}\n}";
        } catch (Exception ex) {
            return null;
        }
        return constructName;
    }

    public boolean regxPatternTest(String endDateTime) {
        Pattern pattern;
        Matcher matcher;

        final String PHONE_PATTERN = "^\\d{4}\\s-\\d{2}\\s-\\d{2}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(endDateTime);
        return matcher.matches();
    }


}
