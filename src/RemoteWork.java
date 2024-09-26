/**********************************************************************
 * @file RemoteWork.java
 * @brief This program creates RemoteWork objects and their attributes.
 * This program also contains the getter methods for all the variables and
 * a compareTo method.
 * @author Blythe Greene
 * @date: September 26, 2024
 ***********************************************************************/
public class RemoteWork implements Comparable<RemoteWork>{
    //Different data entries in CSV file:
    private Integer Employee_ID;
    private String Employment_Type;
    private Integer Hours_Worked_Per_Week;
    private Integer Productivity_Score;
    private Integer Well_Being_Score;


    // default constructor
    public RemoteWork() {
        this.Employee_ID = 0;
        this.Employment_Type = "NA";
        this.Hours_Worked_Per_Week = 0;
        this.Productivity_Score = 0;
        this.Well_Being_Score = 0;
    }

    // constructor with parameters
    public RemoteWork(Integer Employee_ID, String Employment_Type, Integer Hours_Worked_Per_Week, Integer Productivity_Score, Integer Well_Being_Score) {
        this.Employee_ID = Employee_ID;
        this.Employment_Type = Employment_Type;
        this.Hours_Worked_Per_Week = Hours_Worked_Per_Week;
        this.Productivity_Score = Productivity_Score;
        this.Well_Being_Score = Well_Being_Score;
    }

    //Copy constructor
    public RemoteWork(RemoteWork r){
        //Fields get values based on the field's of the object
        this.Employee_ID = r.Employee_ID;
        this.Employment_Type = r.Employment_Type;
        this.Hours_Worked_Per_Week = r.Hours_Worked_Per_Week;
        this.Productivity_Score = r.Productivity_Score;
        this.Well_Being_Score = r.Well_Being_Score;
    }

    //This method should return true if the object is equal to the object passed as a parameter.
    public boolean equals(RemoteWork r){
        if(Integer.compare(this.Employee_ID, r.Employee_ID) != 0){
            return false;
        }
        if(!this.Employment_Type.equals(r.Employment_Type)){
            return false;
        }
        if(Integer.compare(this.Hours_Worked_Per_Week, r.Hours_Worked_Per_Week) != 0){
            return false;
        }
        if(Integer.compare(this.Productivity_Score, r.Productivity_Score) != 0){
            return false;
        }
        if(Integer.compare(this.Well_Being_Score, r.Well_Being_Score) != 0){
            return false;
        }
        return true;
    }

    //Getter methods for my variables
    public Integer getEmployee_ID() {
        return Employee_ID;
    }

    public String getEmployment_Type() {
        return Employment_Type;
    }

    //This is the variable I am comparing
    public Integer getHours_Worked_Per_Week() {
        return Hours_Worked_Per_Week;
    }

    public Integer getProductivity_Score() {
        return Productivity_Score;
    }

    public Integer getWell_Being_Score() {
        return Well_Being_Score;
    }

    //Setter methods for my variables.

    public void setEmployee_ID(Integer employee_ID) {
        Employee_ID = employee_ID;
    }

    public void setEmployment_Type(String employment_Type) {
        Employment_Type = employment_Type;
    }

    public void setHours_Worked_Per_Week(Integer hours_Worked_Per_Week) {
        Hours_Worked_Per_Week = hours_Worked_Per_Week;
    }

    public void setProductivity_Score(Integer productivity_Score) {
        Productivity_Score = productivity_Score;
    }

    public void setWell_Being_Score(Integer well_Being_Score) {
        Well_Being_Score = well_Being_Score;
    }

    //The method toString() returns a String version of the information
    //stored in the class
    @Override
    public String toString() {
        String WorkInfo;
        WorkInfo = "Employee ID: " + Employee_ID + ", Employment Type: " + Employment_Type;
        WorkInfo += ", Hours Worked Per Week: " + Hours_Worked_Per_Week + ", Productivity Score: " + Productivity_Score;
        WorkInfo += ", Well Being Score: " + Well_Being_Score;
        return WorkInfo;
    }

    // This method should return a negative number if the object is less
    // than the object passed as a parameter, a positive number if the
    // object is greater than the object passed as a parameter,
    // and 0 if the objects are equal.
    @Override
    public int compareTo(RemoteWork obj) {
        int h = this.getHours_Worked_Per_Week().compareTo(obj.getHours_Worked_Per_Week());
        if(h == 0) {
            if (this.equals(obj)) {
                return 0;
            }
            return -1;
        }
        else{
            return h;
        }
    }

}
