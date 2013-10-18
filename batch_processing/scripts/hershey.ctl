LOAD DATA
REPLACE
INTO TABLE trans_hershey
FIELDS TERMINATED BY '|'
TRAILING NULLCOLS (
 TRANS_HERSHEY_KEY RECNUM,
 PSU_ID,
 LAST_NAME,
 FIRST_NAME,
 MIDDLE_NAMES,
 suffix,
 preferred_name,
 dept_name,
 mail_code,
 campus_name,
 work_address1,
 work_address2,
 work_city,
 work_state,
 work_zipcode,
 work_phone,
 work_phone_ext,
 title,
 date_of_birth,
 appt_type_code,
 home_address1,
 home_address2,
 home_city,
 home_state,
 home_zipcode,
 home_country,
 gender,
 hr_hire_date,
 hr_term_date)