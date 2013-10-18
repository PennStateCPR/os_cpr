LOAD DATA
REPLACE
INTO TABLE trans_empl (
        TRANS_EMPL_KEY					RECNUM,
        PSU_ID		                    POSITION(01:09) char,
        date_empl_hired                 POSITION(10:17) char,
        code_empl_sex                   POSITION(18:18) char,
        date_empl_birth                 POSITION(19:26) char,
        numb_empl_phone_home            POSITION(27:36) char,
        addr_empl_room_number           POSITION(37:41) char,
        addr_empl_building_name         POSITION(42:63) char,
        addr_empl_campus_name           POSITION(64:93) char,
        addr_empl_office_street         POSITION(94:113) char,
        addr_empl_office_city           POSITION(114:128) char,
        addr_empl_offc_state            POSITION(129:133) char,
        addr_empl_offc_zip              POSITION(134:142) char,
        numb_empl_phone_offc            POSITION(143:152) char,
        name_empl_first_legal           POSITION(153:172) char,
        name_empl_mid_legal             POSITION(173:192) char,
        name_empl_last_legal            POSITION(193:222) char,
        name_empl_sfx_legal             POSITION(223:228) char,
        name_empl_pm_preferred_name     POSITION(229:248) char,
        addr_empl_street_home           POSITION(251:270) char,
        addr_empl_city_home             POSITION(271:285) char,
        addr_empl_state_home            POSITION(286:290) char,
        addr_empl_zip_home_prime        POSITION(291:295) char,
        addr_empl_zip_home_second       POSITION(296:299) char, 
        code_empl_country_home          POSITION(300:301) char,
        code_empl_fnat_visa_type        POSITION(302:306) char,
        code_empl_class                 POSITION(307:310) char,
        code_empl_status                POSITION(311:313) char,
        code_budg_numb_home             POSITION(314:321) char,
        code_budg_location_home         POSITION(322:323) char,
        name_jobd_title_short           POSITION(324:348) char,
        addr_empl_st2_home              POSITION(349:368) char,
        code_empl_rank                  POSITION(449:452) char,
        code_appt                       POSITION(453:455) char,
        code_empl_appt_special          POSITION(456:456) char,
        code_appt_type                  POSITION(457:457) char,
        date_empl_termn                 POSITION(458:465) char,
        code_empl_bnfts_rate            POSITION(466:466) char,
        date_empl_paid                  POSITION(467:474) char,
        code_empl_title_class           POSITION(475:477) char,
        code_empl_title2_class          POSITION(478:480) char,
        code_empl_layoff                POSITION(481:482) char,
        code_empl_direct_phone          POSITION(483:483) char,
        numb_pers_access_acct_id        POSITION(484:491) char,
        addr_campus_addr_line1          POSITION(501:530) char,
        addr_campus_addr_line2          POSITION(531:560) char,
        addr_campus_addr_line3          POSITION(561:590) char,
        name_empl_department            POSITION(591:620) char,
        code_admin_area                 POSITION(621:623) char,
        name_empl_admin_area            POSITION(624:648) char,
        code_mnemonic                   POSITION(649:655) char,
        student_status                  POSITION(656:656) char,
        code_campus                     POSITION(657:658) char,
        alt_job_title                   position(659:683) char,
        job_family                      position(684:685) char)
