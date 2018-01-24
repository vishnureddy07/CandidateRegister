package squareandcube.com.candidateregister;

public class CandidateDataModel {
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
    private String dateOfBirth;
    private String gender;
    private String password;

    private String secondaryTotalNumber;
    private String secondaryInstitutionName;
    private String secondaryUniversityName;
    private String secondaryYOP;

    private String higherSecondaryTotalNumber;
    private String higherSecondaryInstitutionName;
    private String higherSecondaryUniversityName;
    private String higherSecondaryYOP;

    private String graduationTotalNumber;
    private String graduationInstitutionName;
    private String graduationUniversityName;
    private String graduationYOP;

    private String postGraduationTotalNumber;
    private String postGraduationInstitutionName;
    private String postGraduationUniversityName;
    private String postGraduationYOP;

    private String yearGap;
    private String avg;

    // getter method
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }



    public String getSecondaryTotalnumber(){
        return secondaryTotalNumber;
    }
    public String getSecondaryInstitutionName(){
        return secondaryInstitutionName;
    }
    public String getSecondaryUniversityName(){
        return secondaryUniversityName;
    }
    public String getSecondaryYOP(){
        return secondaryYOP;
    }

    public String getGraduationTotalNumber(){
        return graduationTotalNumber;
    }
    public String getGraduationInstitutionName(){
        return graduationInstitutionName;
    }
    public String getGraduationUniversityName(){
        return graduationUniversityName;
    }
    public String getGraduationYOP(){
        return graduationYOP;
    }

    public String getHigherSecondaryTotalNumber(){
        return higherSecondaryTotalNumber;
    }
    public String getHigherSecondaryInstitutionName(){
        return higherSecondaryInstitutionName;
    }
    public String getHigherSecondaryUniversityName(){
        return higherSecondaryUniversityName;
    }
    public String getHigherSecondaryYOP(){
        return higherSecondaryYOP;
    }

    public String getPostGraduationTotalNumber(){
        return postGraduationTotalNumber;
    }
    public String getPostGraduationInstitutionName(){
        return postGraduationInstitutionName;
    }
    public String getPostGraduationUniversityName(){
        return postGraduationUniversityName;
    }
    public String getPostGraduationYOP(){
        return postGraduationYOP;
    }

    public String getYearGap() {
        return yearGap;
    }
    public String getAvg(){
        return avg;
    }

    public void setAvg(String avg){
        this.avg = avg;
    }
    public void setYearGap(String yearGap) {
        this.yearGap = yearGap;
    }
    // setter method

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecondaryTotalNumber(String secondaryTotalNumber){
        this.secondaryTotalNumber = secondaryTotalNumber;
    }

    public void setSecondaryInstitutionName(String secondaryInstitutionName){
        this.secondaryInstitutionName = secondaryInstitutionName;
    }

    public void setSecondaryUniversityName(String secondaryUniversityName){
        this.secondaryUniversityName = secondaryUniversityName;
    }

    public void setSecondaryYOP (String secondaryYOP){
        this.secondaryYOP = secondaryYOP;
    }

    public void setHigherSecondaryTotalNumber(String higherSecondaryTotalNumber){
        this.higherSecondaryTotalNumber = higherSecondaryTotalNumber;
    }

    public void setHigherSecondaryInstitutionName(String higherSecondaryInstitutionName){
        this.higherSecondaryInstitutionName = higherSecondaryInstitutionName;
    }

    public void setHigherSecondaryUniversityName(String higherSecondaryUniversityName){
        this.higherSecondaryUniversityName = higherSecondaryUniversityName;
    }

    public void setHigherSecondaryYOP(String higherSecondaryYOP){
        this.higherSecondaryYOP = higherSecondaryYOP;
    }

    public void setGraduationTotalNumber(String graduationTotalNumber){
        this.graduationTotalNumber = graduationTotalNumber;
    }

    public void setGraduationInstitutionName(String graduationInstitutionName){
        this.graduationInstitutionName = graduationInstitutionName;
    }

    public void setGraduationYOP(String graduationYOP){
        this.graduationYOP = graduationYOP;
    }

    public void setPostGraduationTotalNumber(String postGraduationTotalNumber){
        this.postGraduationTotalNumber = postGraduationTotalNumber;
    }

    public void setPostGraduationInstitutionName(String postGraduationInstitutionName){
        this.postGraduationInstitutionName = postGraduationInstitutionName;
    }

    public void setPostGraduationUniversityName(String postGraduationUniversityName){
        this.postGraduationUniversityName = postGraduationUniversityName;
    }

    public void setPostGraduationYOP(String postGraduationYOP){
        this.postGraduationYOP = postGraduationYOP;
    }

    public void setGraduationUniversityName(String graduationUniversityName) {
        this.graduationUniversityName = graduationUniversityName;
    }

}
