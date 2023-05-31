import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionMatcher {

    protected String regularExpressionString;
    protected static ArrayList<String> wordsToCompareAgainst;
    protected static Pattern pattern;

    public RegularExpressionMatcher() {
        regularExpressionString = "";
        wordsToCompareAgainst = null;
    }

    public void setRegularExpressionString(String regexInput) {
        regularExpressionString = regexInput;
        pattern = Pattern.compile(regularExpressionString);
    }

    public String getRegularExpressionString() {
        return regularExpressionString;
    }

    public void setWordsToCompareAgainst(ArrayList<String> wordsInput) {
        wordsToCompareAgainst = wordsInput;
    }

    public static void main(String[] args) throws Exception {
        RegularExpressionMatcherTest testObj = new RegularExpressionMatcherTest();
        testObj.setUp();
        testObj.test_language1();
        findMatches();

        testObj.test_language2();
        findMatches();

        testObj.test_language3();
        findMatches();

    }

    public static ArrayList<String> findMatches() {

        // TODO: Given the ArrayList wordsToCompareAgainst
        // and the Pattern variable named pattern representing
        // a regular expression, add to the ArrayList matchingList
        // the words that are in the wordsToCompareAgainst list that "match"
        // (could have been generated from) the regular expression
        ArrayList<String> matchingList = new ArrayList<String>();

        for(int i=0; i<wordsToCompareAgainst.size();i++){

                if(pattern.matcher(wordsToCompareAgainst.get(i)).matches() == true){
                        matchingList.add( wordsToCompareAgainst.get(i));
                }
        }

        // wordsToCompareAgainst has been filled with words via a separate
        // method


        return matchingList;
    }

}