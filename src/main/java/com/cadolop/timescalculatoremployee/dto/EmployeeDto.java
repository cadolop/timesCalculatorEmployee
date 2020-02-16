package com.cadolop.timescalculatoremployee.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class EmployeeDto {
	private String id;
	
	@NotBlank(message = "First Name cannot be null or blank")
	private String firstName;

	@NotBlank(message = "Last Name cannot be null or blank")
	private String lastName;

	@Pattern(regexp = "CC|TI|CE|NIT", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Identification Tye must be CC or TI or CE or NIT")
	private String identificationType;
	
	@Size(min = 7, max = 10, message = "Identification range must be 1000000 and 1111999999")
	private String identification;
	
	@Pattern(regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", message = "Date Format dd/mm/yyyy or dd-mm-yyyy or dd.mm.yyyy")
	private String birthDate;
	
	@Pattern(regexp = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", message = "Date Format dd/mm/yyyy or dd-mm-yyyy or dd.mm.yyyy")
	private String startDate;

	@NotBlank(message = "Job Title cannot be null or blank")
	private String jobTitle;

	@Digits(fraction = 2, integer = 10, message = "Salary must be 0.00 and 9999999999.99")
	private String salary;

	public EmployeeDto() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", identificationType="
				+ identificationType + ", identification=" + identification + ", birthDate=" + birthDate
				+ ", startDate=" + startDate + ", jobTitle=" + jobTitle + ", salary=" + salary + "]";
	}
}