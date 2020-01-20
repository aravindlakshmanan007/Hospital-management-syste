package global.coda.hms.constant;

/**
 *
 * @author VC
 *
 */
public class QueryConstants {
	// User field Constants
	public static final String USER_ID = "pk_user_id";
	public static final String USER_NAME = "user_username";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_PHONENUMBER = "user_phonenumber";
	public static final String USER_FIRSTNAME = "user_firstname";
	public static final String USER_LASTNAME = "user_lastname";
	public static final String USER_AGE = "user_age";
	public static final String USER_CITY = "user_city";
	public static final String USER_STATE = "user_state";
	public static final String USER_STREET = "user_street";
	public static final String USER_ROLE_ID = "fk_role_id";
	public static final String USER_ISACTIVE = "user_isactive";
	public static final String USER_CREATEDON = "user_created_on";
	public static final String USER_UPDATEDON = "user_updated_on";
	// Patient Field Constants
	public static final String PATIENT_HEIGHT = "patient_height";
	public static final String PATIENT_WEIGHT = "patient_weight";
	public static final String PATIENT_BLOODGROUP = "patient_blood_group";
	public static final String PATIENT_ISACTIVE = "patient_isactive";
	public static final String PATIENT_CREATEDON = "patient_created_on";
	public static final String PATIENT_UPDATEDON = "patient_updated_on";
	// Doctor Field Constants
	public static final String DOCTOR_EXPERIENCE = "doctor_experience";
	public static final String DOCTOR_SPECIALITY = "doctor_speciality";
	public static final String DOCTOR_ISACTIVE = "doctor_isactive";
	public static final String DOCTOR_CREATEDON = "doctor_created_on";
	public static final String DOCTOR_UPDATEDON = "doctor_updated_on";
	// Doctor Queries
	public static final String DOCTOR_INSERT = "INSERT INTO `hospital_management_system`.`t_doctor_details` (`doctor_experience`, `doctor_speciality`,`fk_userid`) VALUES (?,?,?)";
	public static final String DOCTOR_READ_ALL = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,user.user_street,user.user_city,user.user_state,user.fk_role_id,doctor.doctor_experience,doctor.doctor_speciality,user.user_isactive,doctor.doctor_isactive,user.user_created_on,user.user_updated_on from t_user_details as user INNER JOIN t_doctor_details as doctor on user.pk_user_id = doctor.fk_user_id where user.user_isactive=1 and doctor.doctor_isactive=1 and user.fk_role_id=3";
	public static final String DOCTOR_READ = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,user.user_street,user.user_city,user.user_state,user.fk_role_id,doctor.doctor_experience,doctor.doctor_speciality,user.user_isactive,doctor.doctor_isactive,user.user_created_on,user.user_updated_on from t_user_details as user INNER JOIN t_doctor_details as doctor on user.pk_user_id = doctor.fk_user_id where user.user_isactive=1 and doctor.doctor_isactive=1 and user.fk_role_id=3 and user.pk_user_id=?";
	public static final String DOCTOR_DELETE = "update t_user_details set user_isactive=0 where pk_user_id=? and user_isactive=1 and fk_role_id =3";
	public static final String DOCTOR_UPDATE = "update t_doctor_details set doctor_experience=?,doctor_speciality=? where fk_user_id=?";
	// Patient Queries
	public static final String PATIENT_INSERT = "INSERT INTO `hospital_management_system`.`t_patient_details` (`patient_height`, `patient_blood_group`, `patient_weight`,`fk_userid`) VALUES (?,?,?,?)";
	public static final String PATIENT_READ_ALL = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,user.user_street,user.user_city,user.user_state,user.fk_role_id,patient.patient_height,patient.patient_blood_group,patient.patient_weight,user.user_isactive,user.user_created_on,user.user_updated_on,patient.patient_isactive,patient.pk_patient_id from t_user_details as user INNER JOIN t_patient_details as patient on user.pk_user_id = patient.fk_userid where user.user_isactive=1 and patient.patient_isactive=1 and user.fk_role_id=2";
	public static final String PATIENT_READ = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,user.user_street,user.user_city,user.user_state,user.fk_role_id,patient.patient_height,patient.patient_blood_group,patient.patient_weight,user.user_isactive,user.user_created_on,user.user_updated_on,patient.patient_isactive from t_user_details as user INNER JOIN t_patient_details as patient on user.pk_user_id = patient.fk_userid where user.user_isactive=1 and patient.patient_isactive=1 and user.fk_role_id=2 and user.pk_user_id=?";
	public static final String PATIENT_DELETE = "update t_user_details set user_isactive=0 where pk_user_id=? and user_isactive=1 and fk_role_id =2";
	public static final String PATIENT_UPDATE = "update t_patient_details set  patient_height=?,patient_blood_group=?,patient_weight=? where fk_userid=?";
	// User Queries
	public static final String USER_SELECT_WITH_ID = "select * from t_user_details where user_username=? and user_password=?";
	public static final String USER_INSERT = "INSERT INTO `hospital_management_system`.`t_user_details` (`user_username`, `user_password`, `user_email`, `user_phonenumber`, `user_firstname`, `user_lastname`, `user_age`, `user_city`, `user_state`, `user_street`, `fk_role_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String USER_UPDATE = "update t_user_details set user_username=?,user_password=?,user_email=?,user_phonenumber=?,user_firstname=?,user_lastname=?,user_age=?,user_city=?,user_state=?,user_street=? where pk_user_id=?";
	public static final String MAP_DOCTOR_FOR_PATIENT = "select user.pk_user_id,user.user_username,user.user_password,user.user_email,user.user_phonenumber,user.user_firstname,user.user_lastname,user.user_age,user.user_street,user.user_city,user.user_state,user.fk_role_id,user.user_isactive,user.user_created_on,user.user_updated_on from t_user_details as user INNER JOIN doctor_patient_mapping_details as mapping where user.user_isactive=1 and mapping.doctor_patient_mapping_isactive=1 and user.fk_role_id=2 and mapping.fk_doctor_id=?";

	/**
	 *
	 */
	public QueryConstants() {

	}
}
