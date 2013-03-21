
drop database if exists cpr;

create database cpr;

use cpr;

	SET FOREIGN_KEY_CHECKS=0;

	drop table if exists message_consumer;
	
	drop table if exists message_consumer_mapping;
	
	drop table if exists user_service_status;
	
	drop table if exists mc_warning_notifications;
	
	drop table if exists change_notification_types;
	
	drop table if exists change_notifications;
	
	drop table if exists message_log;
	
	drop table if exists message_log_history;
	
	drop table if exists cpr_access_groups;

    drop table if exists server_principal_ip;

    drop table if exists web_service_access;

    drop table if exists identifier_type;

    drop table if exists person_identifier;

    drop table if exists addresses;

    drop table if exists aff_transition_rules;

    drop table if exists affiliations;

    drop table if exists allowed_char_part;

    drop table if exists allowed_num_part;

    drop table if exists bad_prefixes;

    drop table if exists campus_cs;

    drop table if exists confidentiality;

    drop table if exists country;

    drop table if exists country_map;

    drop table if exists cpr_component_status;

    drop table if exists credential;

    drop table if exists data_element_doc_types;

    drop table if exists data_element_types;

    drop table if exists data_types;

    drop table if exists database_log;

    drop table if exists date_of_birth;

    drop table if exists email_address;

    drop table if exists event_triggers;

    drop table if exists ext_affiliation;

    drop table if exists ext_affiliation_mapping;

    drop table if exists ext_affiliation_types;

    drop table if exists external_iap;

    drop table if exists federation;

    drop table if exists generated_identity;

    drop table if exists group_data_type_access;

    drop table if exists group_member_comments;

    drop table if exists group_members;

    drop table if exists iap;

    drop table if exists iap_data_element;

    drop table if exists iap_document;

    drop table if exists iap_ext_mapping;

    drop table if exists iap_rules;

    drop table if exists id_card_print_log;

    drop table if exists java_exceptions;

    drop table if exists match_results;

    drop table if exists names;

    drop table if exists person;

    drop table if exists person_affiliation;

    drop table if exists person_gender;

    drop table if exists person_iap_data;

    drop table if exists person_iap_vp;

    drop table if exists person_id_card;

    drop table if exists person_linkage;

    drop table if exists person_photo;

    drop table if exists person_userid_iap;

    drop table if exists phones;

    drop table if exists photo_id_issuer;

    drop table if exists photo_id_issuer_data_type;

    drop table if exists policies;

    drop table if exists psu_directory;

    drop table if exists psu_id;

    drop table if exists psu_id_exceptions;

    drop table if exists ra_affiliation;

    drop table if exists ra_comments;

    drop table if exists ra_iap_assign;

     drop table if exists ra_server_principals;

    drop table if exists ra_ui_application;

    drop table if exists registration_authority;

    drop table if exists service_log;

    drop table if exists services;

    drop table if exists user_comments;

    drop table if exists userid;

    drop table if exists userid_policy_status;

    drop table if exists userid_pool;

    drop table if exists usps_state_types;

    drop table if exists usps_states;

    drop table if exists web_service;

    drop table if exists answer_group;
    
	drop table if exists application_properties;
	
	drop table if exists email_notification;
	
	drop table if exists ra_applications;
	
	drop table if exists ra_application_properties;
	
	drop table if exists ra_screens;
	
	drop table if exists ra_screen_fields;
	
	drop table if exists security_questions;
	
	drop table if exists security_question_answers;
	
	drop table if exists ui_applications;
	
	drop table if exists ui_field_types;
	
	drop table if exists ui_log;
	
	drop table if exists ui_screens;
	
	drop table if exists ui_screen_fields;
	
    create table person (
        person_id bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        start_date datetime not null,
        primary key (person_id)
    ) ENGINE=INNODB;

    create table addresses (
        address_key bigint not null auto_increment,
        address1 varchar(60),
        address2 varchar(60),
        address3 varchar(60),
        address_match_code varchar(60),
        campus_code_key bigint,
        city varchar(40),
        city_match_code varchar(60),
        country_key bigint,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        document_type_key bigint,
        end_date datetime,
        group_id bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        postal_code varchar(20),
        primary_flag varchar(1) not null,
        province varchar(40),
        start_date datetime not null,
        state varchar(2),
        validate_attempted_on datetime,
        verified_flag varchar(1) not null,
        do_not_verify_flag varchar(1) not null,
        primary key (address_key)
    ) ENGINE=INNODB;

    create table aff_transition_rules (
        aff_transition_rule_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        current_affiliation_key bigint,
        expire_flag varchar(1) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        request_affiliation_key bigint not null,
        result_affiliation_key bigint not null,
        primary key (aff_transition_rule_key)
    ) ENGINE=INNODB;

    create table affiliations (
        affiliation_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        affiliation varchar(40) not null,
        affiliation_desc varchar(200) not null,
        can_assign_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        enum_string varchar(200),
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        parent_affiliation_key bigint,
        primary key (affiliation_key)
    ) ENGINE=INNODB;

    create table allowed_char_part (
        char_part varchar(30) not null,
        primary key (char_part)
    ) ENGINE=INNODB;

    create table allowed_num_part (
        num_part bigint not null,
        primary key (num_part)
    ) ENGINE=INNODB;

    create table bad_prefixes (
        char_part varchar(3) not null,
        primary key (char_part)
    ) ENGINE=INNODB;

    create table campus_cs (
        campus_code_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        campus varchar(60) not null,
        campus_code varchar(2) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (campus_code_key)
    ) ENGINE=INNODB;

    create table confidentiality (
        confidentiality_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (confidentiality_key)
    ) ENGINE=INNODB;

    create table country (
        country_key bigint not null auto_increment,
        country varchar(60) not null,
        country_code_four varchar(4),
        country_code_three varchar(3) not null,
        country_code_two varchar(2) not null,
        country_full_name varchar(100) not null,
        country_numeric_code varchar(3),
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        remarks longtext,
        start_date datetime,
        us_territory_flag varchar(1) not null,
        primary key (country_key)
    ) ENGINE=INNODB;

    create table country_map (
        country_key bigint not null,
        alpha2 varchar(2),
        alpha3 varchar(3),
        code2 varchar(2),
        code3 varchar(3),
        country varchar(50),
        country_map_key bigint,
        iso_key bigint,
        short_name varchar(50),
        primary key (country_key)
    ) ENGINE=INNODB;

    create table cpr_component_status (
        cpr_component_status_key bigint not null auto_increment,
        active_flag varchar(1),
        cpr_component varchar(60),
        cpr_component_desc varchar(200) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (cpr_component_status_key)
    ) ENGINE=INNODB;

    create table credential (
        credential_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        credential_data varchar(100) not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (credential_key)
    ) ENGINE=INNODB;

    create table data_element_doc_types (
        data_element_doc_type_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_element_type_key bigint not null,
        document_type_key bigint,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (data_element_doc_type_key)
    ) ENGINE=INNODB;

    create table data_element_types (
        data_element_type_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        iap_data_element_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (data_element_type_key)
    ) ENGINE=INNODB;

    create table data_types (
        data_type_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        can_assign_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type varchar(50) not null,
        data_type_desc varchar(200),
        enum_string varchar(200),
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        parent_data_type_key bigint,
        primary key (data_type_key)
    ) ENGINE=INNODB;

    create table identifier_type (
        type_key bigint not null auto_increment,
        type_name varchar(50) not null,
        type_desc varchar(200),
        can_assign_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (type_key)
    ) ENGINE=INNODB;

    create table database_log (
        database_log_key bigint not null auto_increment,
        entry_timestamp datetime not null,
        entry_type varchar(5) not null,
        instance_number bigint,
        output_string varchar(1000),
        package_name varchar(30) not null,
        program_name varchar(30) not null,
        request_duration bigint,
        request_userid varchar(30),
        primary key (database_log_key)
    ) ENGINE=INNODB;

    create table date_of_birth (
        date_of_birth_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        dob datetime,
        dob_char varchar(8),
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (date_of_birth_key)
    ) ENGINE=INNODB;

    create table email_address (
        email_address_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        email_address varchar(256) not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (email_address_key)
    ) ENGINE=INNODB;

    create table event_triggers (
        event_trigger_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        enum_string varchar(30) not null,
        event_trigger varchar(30) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (event_trigger_key)
    ) ENGINE=INNODB;

    create table ext_affiliation (
        ext_affiliation_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        ext_affiliation varchar(30) not null,
        ext_affiliation_desc varchar(150) not null,
        ext_affiliation_type_key bigint not null,
        last_update_by varchar(30),
        last_update_on datetime not null,
        primary key (ext_affiliation_key)
    ) ENGINE=INNODB;

    create table ext_affiliation_mapping (
        ext_affiliation_mapping_key bigint not null auto_increment,
        affiliation_key bigint not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        ext_affiliation_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        start_date datetime not null,
        primary key (ext_affiliation_mapping_key)
    ) ENGINE=INNODB;

    create table ext_affiliation_types (
        ext_affiliation_type_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        ext_affiliation_type varchar(30) not null,
        ext_affiliation_type_desc varchar(300) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (ext_affiliation_type_key)
    ) ENGINE=INNODB;

    create table external_iap (
        external_iap_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        external_iap varchar(30) not null,
        external_iap_desc varchar(150) not null,
        external_iap_url varchar(200),
        federation_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (external_iap_key)
    ) ENGINE=INNODB;

    create table federation (
        federation_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        federation varchar(30) not null,
        federation_desc varchar(100) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (federation_key)
    ) ENGINE=INNODB;

    create table generated_identity (
        generated_identity_key bigint not null auto_increment,
        char_part varchar(30),
        created_by varchar(30) not null,
        created_on datetime not null,
        generated_identity varchar(30) not null,
        num_part bigint,
        person_id bigint not null,
        set_id bigint not null,
        primary key (generated_identity_key)
    ) ENGINE=INNODB;

    create table group_data_type_access
    (
     group_data_type_access_key bigint not null auto_increment,
     cpr_access_groups_key bigint not null,
     data_type_key bigint not null,
     read_flag varchar(1) default 'N' not null check ( read_flag in ('N', 'Y')), 
     write_flag varchar(1) default 'N' not null check ( write_flag in ('N', 'Y')), 
     archive_flag varchar(1) default 'N' not null check ( archive_flag in ('N', 'Y')), 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (group_data_type_access_key)
    ) ENGINE=INNODB;

    create table group_member_comments (
        group_member_comment_key bigint not null auto_increment,
        comments varchar(2000) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        group_member_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (group_member_comment_key)
    ) ENGINE=INNODB;

    create table group_members
    (
     group_member_key bigint not null auto_increment,
     person_id bigint not null,
     userid varchar(30) not null,
     cpr_access_groups_key bigint not null,
     suspend_flag varchar(1) default 'Y' not null check ( suspend_flag in ('N', 'Y')),
     start_date datetime not null,
     end_date datetime,
     last_update_by varchar(30) not null,
     last_update_on datetime not null,
     created_by varchar(30) not null,
     created_on datetime not null,
     primary key (group_member_key)
    ) ENGINE=INNODB;

    create table iap (
        iap_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        iap varchar(30) not null,
        iap_desc varchar(100) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (iap_key)
    ) ENGINE=INNODB;

    create table iap_data_element (
        iap_data_element_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iap_data_element varchar(60) not null,
        iap_data_element_desc varchar(300) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        start_date datetime not null,
        primary key (iap_data_element_key)
    ) ENGINE=INNODB;

    create table iap_document (
        iap_document_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        document_type_key bigint not null,
        end_date datetime,
        iap_document_issuer varchar(100) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        start_date datetime not null,
        primary key (iap_document_key)
    ) ENGINE=INNODB;

    create table iap_ext_mapping (
        iap_ext_mapping_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        external_iap_key bigint not null,
        iap_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        mapping_url varchar(200),
        start_date datetime not null,
        primary key (iap_ext_mapping_key)
    ) ENGINE=INNODB;

    create table iap_rules (
        iap_rule_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iap_data_element_key bigint not null,
        iap_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        required_flag varchar(1) not null,
        start_date datetime not null,
        primary key (iap_rule_key)
    ) ENGINE=INNODB;

    create table id_card_print_log (
        id_card_print_log_key bigint not null auto_increment,
        person_id_card_key bigint not null,
        printed_by varchar(30) not null,
        printed_on datetime not null,
        work_station_ip_address varchar(50) not null,
        work_station_name varchar(30) not null,
        primary key (id_card_print_log_key) 
    ) ENGINE=INNODB;

    create table java_exceptions (
        java_exception_key bigint not null auto_increment,
        created_on datetime not null,
        java_exception_enum varchar(50) not null,
        java_exception_text varchar(100) not null,
        last_update_on datetime not null,
        primary key (java_exception_key)
    ) ENGINE=INNODB;

    create table match_results (
        match_set_key bigint not null,
        person_id bigint not null,
        score bigint not null,
        primary key (match_set_key)
    ) ENGINE=INNODB;

    create table names (
        name_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        document_type_key bigint,
        end_date datetime,
        first_name varchar(60),
        last_name varchar(60),
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        middle_names varchar(60),
        name_match_code varchar(60),
        person_id bigint not null,
        start_date datetime not null,
        suffix varchar(30),
        primary key (name_key)
    ) ENGINE=INNODB;

    create table person_affiliation (
        person_affiliation_key bigint not null auto_increment,
        affiliation_key bigint not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        exception_comments varchar(500),
        exception_flag varchar(1) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        primary_flag varchar(1) not null,
        start_date datetime not null,
        primary key (person_affiliation_key)
    ) ENGINE=INNODB;

    create table person_gender (
        person_gender_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (person_gender_key) 
    ) ENGINE=INNODB;

    create table person_iap_data (
        person_iap_data_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_element_doc_type_key bigint not null,
        iap_data_value_key bigint not null,
        iap_document_key bigint not null,
        person_iap_vp_key bigint not null,
        primary key (person_iap_data_key) 
    ) ENGINE=INNODB;

    create table person_iap_vp (
        person_iap_vp_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        group_member_key bigint not null,
        iap_key bigint not null,
        person_id bigint not null,
        registration_authority_key bigint not null,
        userid varchar(30) not null,
        vp_date datetime not null,
        primary key (person_iap_vp_key)
    ) ENGINE=INNODB;

    create table person_id_card (
        person_id_card_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        id_card_number varchar(30) not null,
        id_serial_number varchar(30),
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (person_id_card_key)
    ) ENGINE=INNODB;

    create table person_identifier (
        person_identifier_key bigint not null auto_increment,
        person_id bigint not null,
        type_key bigint not null,
        identifier_value varchar(100) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        start_date datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        primary key (person_identifier_key)
    ) ENGINE=INNODB;

    create table person_linkage (
        person_linkage_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        linked_person_id bigint not null,
        person_id bigint not null,
        start_date datetime not null,
        primary key (person_linkage_key)
    ) ENGINE=INNODB;

    create table person_photo (
        person_photo_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        date_taken datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        photo blob not null,
        primary key (person_photo_key)
    ) ENGINE=INNODB;

    create table person_userid_iap (
        person_userid_iap_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iap_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        userid varchar(30) not null,
        primary key (person_userid_iap_key)
    ) ENGINE=INNODB;

    create table phones (
        phone_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        extension varchar(40),
        group_id bigint not null,
        international_number_flag varchar(1),
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        phone_number varchar(40) not null,
        primary_flag varchar(1) not null,
        start_date datetime not null,
        primary key (phone_key)
    ) ENGINE=INNODB;

    create table photo_id_issuer (
        photo_id_issuer_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        photo_id_issuer varchar(50) not null,
        photo_id_issuer_desc varchar(100) not null,
        start_date datetime not null,
        primary key (photo_id_issuer_key)
    ) ENGINE=INNODB;

    create table photo_id_issuer_data_type (
        photo_id_issuer_data_type_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        photo_id_issuer_key bigint not null,
        start_date datetime not null,
        primary key (photo_id_issuer_data_type_key)
    ) ENGINE=INNODB;

    create table policies (
        policy_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        policy_name varchar(100) not null,
        policy_text varchar(4000) not null,
        start_date datetime not null,
        primary key (policy_key)
    ) ENGINE=INNODB;

    create table psu_directory (
        psu_directory_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        userid varchar(30),
        primary key (psu_directory_key)
    ) ENGINE=INNODB;

    create table psu_id (
        psu_id_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        psu_id varchar(20) not null,
        start_date datetime not null,
        primary key (psu_id_key)
    ) ENGINE=INNODB;

    create table psu_id_exceptions (
        psu_id varchar(20) not null,
        primary key (psu_id)
    ) ENGINE=INNODB;

    create table ra_affiliation (
        ra_affiliation_key bigint not null auto_increment,
        affiliation_key bigint not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        registration_authority_key bigint not null,
        start_date datetime not null,
        primary key (ra_affiliation_key)
    ) ENGINE=INNODB;

    create table ra_comments (
        ra_comment_key bigint not null auto_increment,
        comments varchar(2000) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime,
        registration_authority_key bigint not null,
        primary key (ra_comment_key)
    ) ENGINE=INNODB;

    create table ra_iap_assign (
        ra_iap_assign_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iap_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        registration_authority_key bigint not null,
        start_date datetime not null,
        primary key (ra_iap_assign_key)
    ) ENGINE=INNODB;

    create table ra_server_principals (
        ra_server_principal_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        ra_server_principal varchar(128) not null,
        registration_authority_key bigint not null,
        start_date datetime not null,
        primary key (ra_server_principal_key)
    ) ENGINE=INNODB;

    create table ra_ui_application (
        ra_ui_application_key bigint not null auto_increment,
        application_desc varchar(1000) not null,
        application_name varchar(200) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        registration_authority_key bigint not null,
        suspend_flag varchar(1) not null,
        primary key (ra_ui_application_key)
    ) ENGINE=INNODB;

    create table registration_authority (
        registration_authority_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        ra_contact_key bigint not null,
        registration_authority varchar(60) not null,
        registration_authority_desc varchar(100) not null,
        start_date datetime not null,
        suspend_flag varchar(1) not null,
        assign_psuid_flag varchar(1) not null,
        assign_userid_flag varchar(1) not null,
        collect_ssn_flag varchar(1) not null,
        primary key (registration_authority_key)
    ) ENGINE=INNODB;

    create table service_log (
        service_log_key bigint not null auto_increment,
        ip_address varchar(50),
        request_duration bigint,
        request_end datetime,
        request_start datetime not null,
        request_string varchar(2000),
        request_userid varchar(30),
        result_string varchar(1000),
        web_service_key bigint not null,
        primary key (service_log_key)
    ) ENGINE=INNODB;

    create table services (
        service_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        days_service_active bigint,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        service_name varchar(100) not null,
        primary key (service_key)
    ) ENGINE=INNODB;

     create table user_comments (
        user_comment_key bigint not null auto_increment,
        comments varchar(2000) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        start_date datetime not null,
        userid varchar(30) not null,
        primary key (user_comment_key)
    ) ENGINE=INNODB;

    create table userid (
        userid varchar(30) not null,
        person_id bigint not null,
        char_part varchar(30),
        created_by varchar(30) not null,
        created_on datetime not null,
        display_name_flag varchar(1) not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        num_part bigint,
        primary_flag varchar(1) not null,
        psu_id_letter varchar(1),
        start_date datetime not null,
        primary key (userid, person_id)
    ) ENGINE=INNODB;

    create table userid_policy_status (
        userid_policy_status_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        date_accepted datetime,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime,
        person_id bigint not null,
        policy_key bigint not null,
        start_date datetime not null,
        userid varchar(30) not null,
        primary key (userid_policy_status_key)
    ) ENGINE=INNODB;

    create table userid_pool (
        userid_pool_key bigint not null auto_increment,
        char_part varchar(30) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        num_part bigint not null,
        userid varchar(30) not null,
        primary key (userid_pool_key)
    ) ENGINE=INNODB;

    create table usps_state_types (
        usps_state_type_key bigint not null auto_increment,
        usps_state_type varchar(50) not null,
        primary key (usps_state_type_key)
    ) ENGINE=INNODB;

    create table usps_states (
        state_code varchar(2) not null,
        state_name varchar(50) not null,
        usps_state_type_key bigint not null,
        primary key (state_code)
    ) ENGINE=INNODB;

    create table web_service (
        web_service_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        start_date datetime not null,
        web_service varchar(30) not null,
        web_service_desc varchar(150) not null,
        primary key (web_service_key)
    ) ENGINE=INNODB;

    create table answer_group ( 
     answer_group_key bigint not null auto_increment,
     answer_method varchar(30) not null, 
     default_selection varchar(100), 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (answer_group_key)
    ) ENGINE=INNODB;

	create table application_properties 
    ( 
     ui_application_key bigint not null,
     key_name varchar(200) not null, 
     key_value varchar(200) not null, 
     active_flag varchar(1) not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ui_application_key,key_name)
    ) ENGINE=INNODB;

	create table email_notification 
    ( 
     mail_notification_key bigint not null auto_increment,
     email_subject varchar(100) not null, 
     text_body varchar(1500) not null, 
     html_body varchar(1500) not null, 
     notification_process varchar(100) not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (mail_notification_key)
    ) ENGINE=INNODB;

	create table ra_applications 
    ( 
     ra_application_key bigint not null auto_increment,
     ui_application_key bigint not null, 
     registration_authority_key bigint not null, 
     suspend_flag varchar(1) default 'Y'  not null check ( suspend_flag in ('N', 'Y')), 
     allow_ssn_collection_flag varchar(1) default 'N'  not null check ( allow_ssn_collection_flag in ('N', 'Y')), 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ra_application_key)
    ) ENGINE=INNODB;

	create table ra_application_properties 
    ( 
     ra_application_properties_key bigint not null auto_increment,
     ra_application_key bigint not null, 
     ui_application_key bigint not null, 
     key_name varchar(200) not null, 
     key_value varchar(200) not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ra_application_properties_key)
    ) ENGINE=INNODB;

	create table ra_screens 
    ( 
     ra_screen_key bigint not null auto_increment,
     ra_application_key bigint not null, 
     ui_screen_name varchar(30) not null, 
     ra_screen_order bigint not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ra_screen_key)
    ) ENGINE=INNODB;

	create table ra_screen_fields 
    ( 
     ra_screen_field_key bigint not null auto_increment,
     ra_screen_key bigint not null, 
     ui_screen_name varchar(30) not null, 
     ui_field_name varchar(30) not null, 
     required_flag varchar(1) default 'Y'  not null check ( required_flag in ('N', 'Y')), 
     display_flag varchar(1) default 'Y'  not null check ( display_flag in ('N', 'Y')), 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ra_screen_field_key)
    ) ENGINE=INNODB;

	create table security_questions 
    ( 
     sec_quest_key bigint not null auto_increment,
     sec_quest_group_key bigint not null, 
     question varchar(100) not null, 
     start_date datetime not null, 
     end_date datetime , 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (sec_quest_key)
    ) ENGINE=INNODB;


	create table security_question_answers 
    ( 
     sec_quest_answer_key bigint not null auto_increment,
     person_id bigint not null, 
     userid varchar(30) not null, 
     sec_quest_key bigint not null, 
     sec_quest_group_key bigint not null, 
     answer varchar(100) not null, 
     created_on datetime not null,
     primary key (sec_quest_answer_key)
    ) ENGINE=INNODB;

	create table ui_applications 
    ( 
     ui_application_key bigint not null auto_increment,
     application_name varchar(200) not null, 
     application_desc varchar(1000) not null, 
     suspend_flag varchar(1) default 'Y'  not null check ( suspend_flag in ('N', 'Y')), 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ui_application_key)
    ) ENGINE=INNODB;

	create table ui_field_types 
    ( 
     ui_field_type varchar(25) not null default ' ', 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ui_field_type)
    ) ENGINE=INNODB;

	create table ui_log 
    ( 
     ui_log_key bigint not null auto_increment,
     ui_application_key bigint not null, 
     request_datetime datetime not null, 
     request_duration bigint , 
     request_string varchar(2000), 
     result_string varchar(1000), 
     ip_address varchar(50), 
     request_userid varchar(30), 
     browser_type varchar(200), 
     error_flag varchar(1) default 'N'  not null check ( error_flag in ('N', 'Y')),
     primary key (ui_log_key)
    ) ENGINE=INNODB;

	create table ui_screens 
    ( 
     ui_screen_name varchar(30) not null default ' ', 
     ui_application_key bigint not null,
     required_flag varchar(1) default 'Y'  not null check ( required_flag in ('N', 'Y')), 
     active_flag varchar(1) not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null,
     primary key (ui_screen_name)
    ) ENGINE=INNODB;

	create table ui_screen_fields 
    ( 
     ui_screen_name varchar(30) not null default ' ', 
     ui_field_name varchar(30) not null default ' ', 
     ui_field_type varchar(25) not null, 
     max_length bigint , 
     field_width bigint , 
     field_height bigint , 
     required_flag varchar(1) default 'Y'  not null check ( required_flag in ('N', 'Y')), 
     display_flag varchar(1) default 'Y'  not null check ( display_flag in ('N', 'Y')), 
     active_flag varchar(1) not null, 
     last_update_by varchar(30) not null, 
     last_update_on datetime not null, 
     created_by varchar(30) not null, 
     created_on datetime not null 
    ) ENGINE=INNODB;

    create table cpr_access_groups
    (
     cpr_access_groups_key bigint  not null auto_increment, 
     ra_server_principal_key bigint  not null, 
     access_group varchar(60)  not null, 
     suspend_flag varchar(1) default 'Y'  not null check ( suspend_flag in ('N', 'Y')), 
     start_date datetime  not null, 
     end_date datetime, 
     last_update_by varchar(30)  not null, 
     last_update_on datetime  not null, 
     created_by varchar(30)  not null, 
     created_on datetime  not null,
     primary key (cpr_access_groups_key)
    ) ENGINE=INNODB;

    create table web_service_access
    (
     web_service_access_key bigint not null auto_increment,
     cpr_access_groups_key bigint not null,
     web_service_key bigint  not null,
     suspend_flag varchar(1) default 'Y'  not null check ( suspend_flag in ('N', 'Y')),
     start_date datetime  not null,
     end_date datetime,
     last_update_by varchar(30)  not null,
     last_update_on datetime  not null,
     created_by varchar(30)  not null,
     created_on datetime  not null,
     primary key (web_service_access_key)
    ) ENGINE=INNODB;

    create table server_principal_ip
    (
     server_principal_ip_key bigint not null auto_increment, 
     ra_server_principal_key bigint not null, 
     ip_address varchar(15)  not null,  
     start_date datetime not null,  
     end_date datetime,  
     last_update_by varchar(30) not null,  
     last_update_on datetime not null,  
     created_by varchar(30) not null,  
     created_on datetime not null,
     primary key (server_principal_ip_key)
    ) ENGINE=INNODB;

    create table message_consumer
    (
     message_consumer_key bigint not null auto_increment,
     consumer varchar(50) not null,
     consumer_reg_date datetime not null,
     consumer_email varchar(256),
     suspend_flag varchar(1) default 'Y' not null check (suspend_flag in ('N', 'Y')),
     consumer_destination varchar(128),
     destination_type varchar(30),
     maximum_retries bigint,
     retry_interval bigint,
     maximum_failure bigint,
     maximum_destination_size bigint,
     start_date datetime not null,
     end_date datetime,
     last_update_by varchar(30) not null,
     last_update_on datetime  not null,
     created_by varchar(30)  not null,
     created_on datetime  not null,
     primary key(message_consumer_key)
    )  ENGINE=INNODB;
    
    create table message_consumer_mapping
    (
     msg_con_mapping_key bigint not null auto_increment,
     message_consumer_key bigint not null,
     service_key bigint not null,
     web_service_key bigint  not null,
     max_days_provisioned bigint ,
     start_date datetime not null,
     end_date datetime,
     last_update_by varchar(30) not null,
     last_update_on datetime not null,
     created_by varchar(30) not null,
     created_on datetime not null,
     primary key(msg_con_mapping_key)
    ) ENGINE=INNODB;
    
    create table user_service_status
    (
     user_service_status_key bigint not null auto_increment,
     person_id bigint  not null,
     userid varchar(30)  not null,
     service_key bigint  not null,
     message_consumer_key bigint  not null,
     expiration_date datetime,
     provision_date datetime not null,
     deprovision_date datetime,
     last_update_by varchar(30) not null,
     last_update_on datetime not null,
     created_by varchar(30) not null,
     created_on datetime not null,
     primary key(user_service_status_key)
    ) ENGINE=INNODB;
    
    create table mc_warning_notifications
    (
     mc_warning_notification_key bigint not null auto_increment,
     message_consumer_key bigint  not null,
     event_trigger_key bigint not null,
     entry_timestamp datetime not null,
     start_date datetime not null,
     end_date datetime,
     primary key (mc_warning_notification_key)
    ) ENGINE=INNODB;
    
    create table change_notification_types
    (
     change_notification_types_key bigint not null auto_increment,
     notification_type varchar(60) not null,
     notification_desc varchar(100) not null,
     active_flag varchar(1) default 'N' not null check ( active_flag in ('N', 'Y')) ,
     last_update_by varchar(30) not null,
     last_update_on datetime not null,
     created_by varchar(30) not null,
     created_on datetime not null,
     primary key (change_notification_types_key)
    ) ENGINE=INNODB;
    
    create table change_notifications
    (
     change_notifications_key bigint not null auto_increment,
     message_consumer_key bigint not null,
     change_notification_types_key bigint not null,
     start_date datetime not null,
     end_date datetime,
     last_update_by varchar(30) not null,
     last_update_on datetime not null,
     created_by varchar(30) not null,
     created_on datetime not null,
     primary key(change_notifications_key)
    ) ENGINE=INNODB;
    
    create table message_log
    (
     message_log_key bigint not null auto_increment,
     web_service_key bigint not null,
     message_consumer_key bigint not null,
     service_key bigint not null,
     message_sent varchar(1000),
     number_of_tries bigint not null,
     success_flag varchar(1) default 'N' check ( success_flag in ('N', 'Y')),
     request_userid varchar(30) not null,
     last_update_on datetime not null,
     created_on datetime not null,
     primary key (message_log_key)
    ) ENGINE=INNODB;
    
    create table message_log_history
    (
     message_log_history_key bigint not null auto_increment,
     message_log_key bigint not null,
     message_sent_timestamp datetime not null,
     message_id varchar(100),
     message_received_timestamp datetime,
     message_received varchar(1000),
     error_code varchar(100),
     error_message varchar(1000),
     try_number bigint,
     last_update_on datetime not null,
     created_on datetime not null,
     primary key(message_log_history_key)
    ) ENGINE=INNODB;
-- end of tables --

    
alter table addresses add foreign key (person_id) references person(person_id);
alter table addresses add foreign key (data_type_key) references data_types(data_type_key);
alter table confidentiality add foreign key (person_id) references person(person_id);
alter table confidentiality add foreign key (data_type_key) references data_types(data_type_key);
alter table credential add foreign key (person_id) references person(person_id);
alter table credential add foreign key (data_type_key) references data_types(data_type_key);
alter table data_element_types add foreign key (data_type_key) references data_types(data_type_key);
alter table date_of_birth add foreign key (person_id) references person(person_id);
alter table email_address add foreign key (person_id) references person(person_id);
alter table email_address add foreign key (data_type_key) references data_types(data_type_key);
alter table ext_affiliation add foreign key (ext_affiliation_type_key) references ext_affiliation_types(ext_affiliation_type_key);
alter table ext_affiliation_mapping add foreign key (affiliation_key) references affiliations(affiliation_key);
alter table ext_affiliation_mapping add foreign key (ext_affiliation_key) references ext_affiliation(ext_affiliation_key);
alter table external_iap add foreign key (federation_key) references federation(federation_key);
alter table generated_identity add foreign key (person_id) references person(person_id);
alter table group_data_type_access add constraint gda_cag_fk foreign key ( cpr_access_groups_key) references cpr_access_groups ( cpr_access_groups_key);
alter table group_data_type_access add constraint gda_data_types_fk foreign key ( data_type_key) references data_types ( data_type_key);
alter table group_member_comments add foreign key (group_member_key) references group_members(group_member_key);
alter table group_members add constraint grpmbrs_cag_fk foreign key ( cpr_access_groups_key) references cpr_access_groups ( cpr_access_groups_key);
alter table group_members add constraint grpmbrs_userid_fk foreign key ( userid, person_id) references userid ( userid, person_id);
alter table id_card_print_log add foreign key (person_id_card_key) references person_id_card(person_id_card_key);
alter table match_results add foreign key (person_id) references person(person_id);
alter table names add foreign key (person_id) references person(person_id);
alter table names add foreign key (data_type_key) references data_types(data_type_key);
alter table person_affiliation add foreign key (person_id) references person(person_id);
alter table person_affiliation add foreign key (affiliation_key) references affiliations(affiliation_key);
alter table person_gender add foreign key (person_id) references person(person_id);
alter table person_gender add foreign key (data_type_key) references data_types(data_type_key);
alter table person_iap_data add foreign key (data_element_doc_type_key) references data_element_doc_types(data_element_doc_type_key);
alter table person_iap_data add foreign key (iap_document_key) references iap_document(iap_document_key);
alter table person_iap_vp add foreign key (person_id) references person(person_id);
alter table person_iap_vp add foreign key (userid) references userid(userid);
alter table person_iap_vp add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table person_id_card add foreign key (person_id) references person(person_id);
alter table person_id_card add foreign key (data_type_key) references data_types(data_type_key);
alter table person_identifier add foreign key (person_id) references person(person_id);
alter table person_identifier add foreign key (type_key) references identifier_type(type_key);
alter table person_linkage add foreign key (person_id) references person(person_id);
alter table person_linkage add foreign key (linked_person_id) references person(person_id);
alter table person_linkage add foreign key (data_type_key) references data_types(data_type_key);
alter table person_photo add foreign key (person_id) references person(person_id);
alter table person_userid_iap add foreign key (person_id) references person(person_id);
alter table person_userid_iap add foreign key (userid) references userid(userid);
alter table phones add foreign key (person_id) references person(person_id);
alter table phones add foreign key (data_type_key) references data_types(data_type_key);
alter table photo_id_issuer_data_type add foreign key (data_type_key) references data_types(data_type_key);
alter table psu_directory add foreign key (person_id) references person(person_id);
alter table psu_id add foreign key (person_id) references person(person_id);
alter table ra_affiliation add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_comments add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_iap_assign add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_server_principals add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_ui_application add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table security_question_answers add foreign key (person_id) references person(person_id);
alter table security_question_answers add foreign key (userid) references userid(userid);
alter table service_log add foreign key (web_service_key) references web_service(web_service_key);
alter table user_comments add foreign key (person_id) references person(person_id);
alter table user_comments add foreign key (userid) references userid(userid);
alter table user_comments add foreign key (data_type_key) references data_types(data_type_key);
alter table userid add foreign key (person_id) references person(person_id);
alter table userid_policy_status add foreign key (person_id) references person(person_id);
alter table userid_policy_status add foreign key (userid) references userid(userid);
alter table ui_screen_fields add constraint ui_screen_fields_pk primary key (ui_screen_name, ui_field_name);
alter table application_properties add constraint appprop_rauiapp_fk foreign key ( ui_application_key) references ui_applications ( ui_application_key);
alter table ra_application_properties add foreign key ( ra_application_key) references ra_applications ( ra_application_key);
alter table ra_applications add foreign key ( registration_authority_key) references registration_authority ( registration_authority_key);
alter table ra_applications add foreign key ( ui_application_key) references ui_applications ( ui_application_key);
alter table ra_screen_fields add foreign key ( ra_screen_key) references ra_screens ( ra_screen_key);
alter table ra_screen_fields add foreign key ( ui_screen_name, ui_field_name) references ui_screen_fields ( ui_screen_name, ui_field_name);
alter table ra_screens add foreign key ( ra_application_key) references ra_applications ( ra_application_key);
alter table security_question_answers add foreign key ( sec_quest_key) references security_questions ( sec_quest_key);
alter table security_question_answers add foreign key ( userid, person_id) references userid ( userid, person_id);
alter table ui_log add foreign key ( ui_application_key) references ui_applications ( ui_application_key);
alter table ui_screen_fields add foreign key ( ui_field_type) references ui_field_types ( ui_field_type);
alter table ui_screens add foreign key ( ui_application_key) references ui_applications ( ui_application_key);
alter table cpr_access_groups add constraint cag_spkey_group_uk unique ( ra_server_principal_key , access_group );
alter table cpr_access_groups add constraint cag_rasrvrprinc_fk foreign key ( ra_server_principal_key) references ra_server_principals ( ra_server_principal_key);
alter table web_service_access add constraint wsa_cag_fk foreign key ( cpr_access_groups_key) references cpr_access_groups ( cpr_access_groups_key);
alter table web_service_access add constraint wsa_websrvc_fk foreign key ( web_service_key) references web_service ( web_service_key);
alter table server_principal_ip add constraint spi_raserverprinc_fk foreign key ( ra_server_principal_key ) references ra_server_principals ( ra_server_principal_key);
alter table message_consumer add constraint mec_consumer_UK unique (consumer);
alter table message_consumer_mapping add constraint mcm_mec_fk foreign key ( message_consumer_key) references message_consumer ( message_consumer_key);
alter table message_consumer_mapping add constraint mcm_services_fk foreign key ( service_key) references services ( service_key);
alter table message_consumer_mapping add constraint mcm_websrvc_fk foreign key ( web_service_key) references web_service ( web_service_key);
alter table user_service_status add constraint USERSRVCSTAT_SERVICES_FK foreign key ( service_key) references services ( service_key);
alter table user_service_status add constraint USERSRVCSTAT_userid_FK foreign key ( userid, person_id) references userid ( userid, person_id);
alter table user_service_status add constraint usersrvcstat_mec_fk foreign key ( message_consumer_key) references message_consumer ( message_consumer_key);
alter table mc_warning_notifications add constraint mcw_eventtrig_fk foreign key ( event_trigger_key) references event_triggers ( event_trigger_key);
alter table mc_warning_notifications add constraint mcw_mec_fk foreign key ( message_consumer_key) references message_consumer ( message_consumer_key);
alter table change_notifications
    add constraint cno_consumer_chngnottype_UK UNIQUE ( message_consumer_key , change_notification_types_key ) ;
alter table change_notifications add constraint cno_cnt_fk foreign key ( change_notification_types_key) references change_notification_types ( change_notification_types_key);
alter table change_notifications add constraint cno_mec_fk foreign key ( message_consumer_key) references message_consumer ( message_consumer_key);
alter table message_log add constraint msglog_mec_fk foreign key ( message_consumer_key) references message_consumer ( message_consumer_key);
alter table message_log add constraint msglog_websrvc_fk foreign key ( web_service_key) references web_service ( web_service_key);
alter table message_log add constraint msglog_srv_fk foreign key ( service_key ) references services (service_key);
alter table message_log_history add constraint msghistlog_message_log_FK foreign key ( message_log_key) references message_log ( message_log_key);
-- end of alter tables --


CREATE or replace VIEW v_database_log (number_tries, entry_timestamp, request_duration) AS 
  (
SELECT substr(REVERSE(output_string),2,1) as number_tries, entry_timestamp, request_duration
 FROM database_log
WHERE entry_type = 'INFO'
);

  CREATE OR REPLACE VIEW v_ext_affiliation_mapping (affiliation_key, affiliation, aff_enum_string, aff_active_flag, ext_affiliation_type_key, ext_affiliation_type, extafftype_active_flag, ext_affiliation_key, ext_affiliation, extaff_active_flag) AS 
  SELECT
	affiliations.affiliation_key,
	affiliations.affiliation,
	affiliations.enum_string AS aff_enum_string,
	affiliations.active_flag AS aff_active_flag,
	ext_affiliation_types.ext_affiliation_type_key,
	ext_affiliation_types.ext_affiliation_type,
	ext_affiliation_types.active_flag AS extafftype_active_flag,
	ext_affiliation.ext_affiliation_key,
	ext_affiliation.ext_affiliation,
	ext_affiliation.active_flag AS extaff_active_flag
FROM affiliations LEFT JOIN ext_affiliation_mapping
  		ON affiliations.affiliation_key = ext_affiliation_mapping.affiliation_key
	LEFT JOIN ext_affiliation
  		ON ext_affiliation_mapping.ext_affiliation_key = ext_affiliation.ext_affiliation_key
	LEFT JOIN ext_affiliation_types
		ON ext_affiliation.ext_affiliation_type_key = ext_affiliation_types.ext_affiliation_type_key;

  CREATE OR REPLACE VIEW v_external_iap_federation (person_id, userid, external_iap, federation) AS 
  SELECT person_userid_iap.person_id,
	person_userid_iap.userid,
	external_iap.external_iap,
	federation.federation
FROM federation JOIN external_iap USING (federation_key)
   JOIN iap_ext_mapping USING (external_iap_key)
   JOIN iap USING (iap_key)
   JOIN person_userid_iap USING (iap_key)
WHERE person_userid_iap.end_date IS NULL
  AND iap_ext_mapping.end_date IS NULL
  AND iap.active_flag = 'Y'
  AND external_iap.active_flag = 'Y'
  AND federation.active_flag = 'Y';

  CREATE OR REPLACE VIEW v_group_data_type_access (cpr_access_groups_key, access_group, cpraccgrp_susped_flag, group_data_type_access_key, data_type_key, read_flag, write_flag, archive_flag, parent_data_type_key, data_type, datatype_enum_string, datatype_can_assign_flag, datatype_active_flag) AS 
  SELECT
	cpr_access_groups.cpr_access_groups_key,
	cpr_access_groups.access_group,
	cpr_access_groups.suspend_flag AS cpraccgrp_suspend_flag,
	group_data_type_access.group_data_type_access_key,
	data_types.data_type_key,
	group_data_type_access.read_flag,
	group_data_type_access.write_flag,
	group_data_type_access.archive_flag,
	data_types.parent_data_type_key,
	data_types.data_type,
	data_types.enum_string AS datatype_enum_string,
	data_types.can_assign_flag AS datatype_can_assign_flag,
	data_types.active_flag AS datatype_active_flag
 FROM cpr_access_groups
	JOIN group_data_type_access ON cpr_access_groups.cpr_access_groups_key = group_data_type_access.cpr_access_groups_key
	JOIN data_types ON group_data_type_access.data_type_key = data_types.data_type_key;

  CREATE OR REPLACE VIEW v_internal_affiliations (person_id, primary_flag, peraff_end_date, affiliation_key, affiliation, enum_string, aff_active_flag, can_assign_flag) AS 
  SELECT person_affiliation.person_id,
	person_affiliation.primary_flag,
	person_affiliation.end_date AS peraff_end_date,
	affiliations.affiliation_key,
	affiliations.affiliation,
	affiliations.enum_string,
	affiliations.active_flag AS aff_active_flag,
	affiliations.can_assign_flag
FROM person_affiliation JOIN affiliations
      ON person_affiliation.affiliation_key = affiliations.affiliation_key;

  CREATE OR REPLACE VIEW v_person_affiliation (person_affiliation_key, person_id, affiliation_key, primary_flag, peraff_end_date, parent_affiliation_key, affiliation, aff_enum_string) AS 
  SELECT
	person_affiliation.person_affiliation_key,
	person_affiliation.person_id,
	person_affiliation.affiliation_key,
	person_affiliation.primary_flag,
	person_affiliation.end_date AS peraff_end_date,
	affiliations.parent_affiliation_key,
	affiliations.affiliation,
	affiliations.enum_string AS aff_enum_string
FROM person_affiliation JOIN affiliations
		ON person_affiliation.affiliation_key = affiliations.affiliation_key;

  CREATE OR REPLACE VIEW v_person_id_card_print_log (person_id_card_key, person_id, id_card_number, id_card_print_log_key, work_station_ip_address, work_station_name, printed_by, printed_on) AS 
  SELECT
	person_id_card.person_id_card_key,
	person_id_card.person_id,
	person_id_card.id_card_number,
	id_card_print_log.id_card_print_log_key,
	id_card_print_log.work_station_ip_address,
	id_card_print_log.work_station_name,
	id_card_print_log.printed_by,
	id_card_print_log.printed_on
FROM person_id_card JOIN id_card_print_log
		ON person_id_card.person_id_card_key = id_card_print_log.person_id_card_key;

  CREATE OR REPLACE VIEW v_ra_group_web_service (group_member_key, cpr_access_groups_key, registration_authority_key, ra_server_principal_key, userid, ra_suspend_flag, grpmbrs_suspend_flag, cpraccgprs_suspend_flag, websrvacc_suspend_flag, web_service) AS 
  SELECT group_members.group_member_key,
	   cpr_access_groups.cpr_access_groups_key,
	   ra_server_principals.registration_authority_key,
	   ra_server_principals.ra_server_principal_key,
       group_members.userid,
       ra.suspend_flag as ra_suspend_flag,
       group_members.suspend_flag AS grpmbrs_suspend_flag,
       cpr_access_groups.suspend_flag AS cpraccgprs_suspend_flag,
       web_service_access.suspend_flag AS websrvacc_suspend_flag,
       web_service.web_service
 FROM registration_authority ra JOIN ra_server_principals
      	ON ra.registration_authority_key = ra_server_principals.registration_authority_key
    JOIN cpr_access_groups ON ra_server_principals.ra_server_principal_key = cpr_access_groups.ra_server_principal_key
    JOIN group_members ON cpr_access_groups.cpr_access_groups_key = group_members.cpr_access_groups_key
    JOIN web_service_access ON cpr_access_groups.cpr_access_groups_key = web_service_access.cpr_access_groups_key
    JOIN web_service ON web_service_access.web_service_key = web_service.web_service_key
 WHERE group_members.end_date IS NULL
 AND cpr_access_groups.end_date IS NULL
 AND ra_server_principals.end_date IS NULL
 AND web_service_access.end_date IS NULL
 AND web_service.end_date IS NULL
 AND ra.end_date IS NULL;

  CREATE OR REPLACE VIEW v_sp_notification (message_consumer_key, consumer, consumer_destination, destination_type, web_service, web_service_key, service_name, service_key) AS 
  SELECT message_consumer.message_consumer_key,
       message_consumer.consumer,
       message_consumer.consumer_destination,
       message_consumer.destination_type,
       web_service.web_service,
       web_service.web_service_key,
       services.service_name,
       services.service_key
  FROM message_consumer
  JOIN message_consumer_mapping ON message_consumer.message_consumer_key = message_consumer_mapping.message_consumer_key
    AND message_consumer.end_date IS NULL
    AND message_consumer.suspend_flag = 'N'
    AND message_consumer_mapping.end_date IS NULL
  JOIN web_service ON message_consumer_mapping.web_service_key = web_service.web_service_key
    AND web_service.end_date IS NULL
  JOIN services ON message_consumer_mapping.service_key = services.service_key AND
  	services.active_flag = 'Y';

create index email_notification_01_idx on email_notification (notification_process asc);
create index ra_applications_10_idx on ra_applications ( registration_authority_key asc );
create index security_questions_01_idx on security_questions ( sec_quest_group_key asc , end_date asc );
create index sec_quest_answers_01_idx on security_question_answers ( person_id asc , userid asc );
create index addresses_02_idx on addresses ( person_id asc, end_date asc, data_type_key asc); 
create index addresses_03_idx on addresses ( address1 asc, address2 asc, address3 asc);
create index addresses_10_idx on addresses ( person_id asc); 
create index addresses_20_idx on addresses ( data_type_key asc);
create index addresses_21_idx on addresses ( campus_code_key asc);
create index addresses_22_idx on addresses ( country_key asc);
create index addresses_match_idx on addresses ( person_id asc, address_match_code asc, city_match_code asc, state asc, postal_code asc);
create index addresses_pk on addresses ( address_key asc);
create index affiliations_01_idx on affiliations ( active_flag asc, affiliation_key asc);
create index affiliations_pk on affiliations ( affiliation_key asc); 
create index aff_transition_rules_01_idx on aff_transition_rules ( current_affiliation_key asc, request_affiliation_key asc, result_affiliation_key asc, expire_flag asc);
create index aff_transition_rules_pk on aff_transition_rules ( aff_transition_rule_key asc);
create index bad_prefixes_idx on bad_prefixes ( char_part asc);
create index campus_cs_01_idx on campus_cs ( campus_code asc, active_flag asc);
create index campus_cs_pk on campus_cs ( campus_code_key asc);
create index confidentiality_01_idx on confidentiality ( end_date asc, person_id asc, data_type_key asc);
create index confidentiality_10_idx on confidentiality ( person_id asc); 
create index confidentiality_20_idx on confidentiality ( data_type_key asc);
create index confidentiality_pk on confidentiality ( confidentiality_key asc);
create index country_pk on country ( country_key asc);
create index cpr_component_status_01_idx on cpr_component_status ( cpr_component asc);
create index cpr_component_status_pk on cpr_component_status ( cpr_component_status_key asc);
create index credential_01_idx on credential ( end_date asc, person_id asc, data_type_key asc);
create index credential_20_idx on credential ( person_id asc);
create index credential_21_idx on credential ( data_type_key asc);
create index credent_pk on credential ( credential_key asc);
create index database_log_entrystamp_idx on database_log ( entry_timestamp asc, program_name asc);
create index database_log_pk on database_log ( database_log_key asc);
create index data_element_doc_type_pk on data_element_doc_types ( data_element_doc_type_key asc);
create index data_element_types_pk on data_element_types ( data_element_type_key asc);
create index data_types_01_idx on data_types ( enum_string asc);
create index data_types_20_idx on data_types ( parent_data_type_key asc);
create index data_types_pk on data_types ( data_type_key asc);
create index date_of_birth_01_idx on date_of_birth ( person_id asc, end_date asc);
create index date_of_birth_10_idx on date_of_birth ( person_id asc);
create index date_of_birth_pk on date_of_birth ( date_of_birth_key asc);
create index email_address_10_idx on email_address ( person_id asc);
create index email_address_20_idx on email_address ( data_type_key asc);
create index email_address_pk on email_address ( email_address_key asc);
create index event_triggers_pk on event_triggers ( event_trigger_key asc);
create index external_iap_01_idx on external_iap ( external_iap_key asc, active_flag asc, external_iap asc);
create index external_iap_pk on external_iap ( external_iap_key asc);
create index ext_affiliation_pk on ext_affiliation ( ext_affiliation_key asc);
create index ext_affiliation_mapping_01_idx on ext_affiliation_mapping ( affiliation_key asc, ext_affiliation_key asc);
create index ext_affiliation_map_pk on ext_affiliation_mapping ( ext_affiliation_mapping_key asc);
create index ext_affiliation_type_pk on ext_affiliation_types ( ext_affiliation_type_key asc);
create index federation_01_idx on federation ( federation_key asc, active_flag asc, federation asc);
create index federation_pk on federation ( federation_key asc);
create index generated_identity_01_idx on generated_identity ( generated_identity asc);
create index generated_identity_02_idx on generated_identity ( char_part asc, num_part asc);
create index generated_identity_10_idx on generated_identity ( person_id asc);
create index generated_identity_pk on generated_identity ( generated_identity_key asc);
create index generated_identity_set_id_idx on generated_identity ( set_id asc); 
create index group_data_type_access_01_idx on group_data_type_access ( data_type_key asc , cpr_access_groups_key asc);
create index group_data_type_access_pkx on group_data_type_access ( group_data_type_access_key asc);
create index gda_data_types_fkx on group_data_type_access ( data_type_key asc); 
create index gda_cag_fkx on group_data_type_access ( cpr_access_groups_key asc);
create index group_members_01_idx on group_members ( cpr_access_groups_key asc , userid asc , end_date asc);
create index group_members_20_idx on group_members ( userid asc , person_id asc);
create index group_members_02_idx on group_members ( userid asc , end_date asc);
create index group_members_pk on group_members ( group_member_key asc);
create index group_member_comments_pk on group_member_comments ( group_member_comment_key asc);
create index iap_pk on iap ( iap_key asc);
create index iap_data_element_pk on iap_data_element ( iap_data_element_key asc);
create index iap_document_pk on iap_document ( iap_document_key asc);
create index iap_ext_mapping_01_idx on iap_ext_mapping ( iap_key asc, external_iap_key asc, end_date asc);
create index iap_ext_mapping_pk on iap_ext_mapping ( iap_ext_mapping_key asc);
create index iap_rules_pk on iap_rules ( iap_rule_key asc);
create index id_card_print_history_pk on id_card_print_log ( id_card_print_log_key asc);
create index id_card_print_log_20_idx on id_card_print_log ( person_id_card_key asc);
create index java_exceptions_pk on java_exceptions ( java_exception_key asc);
create index match_results_10_idx on match_results ( person_id asc);
create index match_results_idx on match_results ( match_set_key asc, score asc, person_id asc);
create index names_10_idx on names ( person_id asc);
create index names_21_idx on names ( data_type_key asc);
create index names_match_idx on names ( name_match_code asc, person_id asc);
create index names_namessp_idx on names ( person_id asc, end_date asc);
create index names_pk on names ( name_key asc);
create index person_id_end_date_idx on person ( person_id asc, end_date asc);
create index person_pk on person ( person_id asc);
create index person_affiliation_01_idx on person_affiliation ( person_id asc, affiliation_key asc, end_date asc);
create index person_affiliation_10_idx on person_affiliation ( person_id asc);
create index person_affiliation_21_idx on person_affiliation ( affiliation_key asc);
create index person_affiliation_22_idx on person_affiliation ( person_id asc, end_date asc);
create index person_affiliation_pk on person_affiliation ( person_affiliation_key asc);
create index person_gender_01_idx on person_gender ( person_id asc, end_date asc, data_type_key asc);
create index person_gender_10_idx on person_gender ( person_id asc);
create index person_gender_20_idx on person_gender ( data_type_key asc);
create index person_gender_pk on person_gender ( person_gender_key asc);
create index person_iap_data_pk on person_iap_data ( person_iap_data_key asc);
create index person_iap_vp_10_idx on person_iap_vp ( person_id asc); 
create index person_iap_vp_20_idx on person_iap_vp ( iap_key asc);
create index person_iap_vp_21_idx on person_iap_vp ( registration_authority_key asc);
create index person_iap_vp_22_idx on person_iap_vp ( group_member_key asc);
create index person_iap_vp_23_idx on person_iap_vp ( userid asc, person_id asc);
create index person_iap_vp_pk on person_iap_vp ( person_iap_vp_key asc);
create index person_id_card_01_idx on person_id_card ( person_id asc, end_date asc, data_type_key asc);
create index person_id_card_pk on person_id_card ( person_id_card_key asc);
create index person_linkage_01_idx on person_linkage ( linked_person_id asc, person_id asc, data_type_key asc);
create index person_linkage_02_idx on person_linkage ( end_date asc);
create index person_linkage_10_idx on person_linkage ( person_id asc); 
create index person_linkage_20_idx on person_linkage ( data_type_key asc);
create index person_linkage_pk on person_linkage ( person_linkage_key asc);
create index person_photo_20_idx on person_photo ( person_id asc);
create index person_photo_pk on person_photo ( person_photo_key asc);
create index person_userid_iap_01_idx on person_userid_iap ( userid asc, iap_key asc, end_date asc);
create index person_userid_iap_20_idx on person_userid_iap ( userid asc, person_id asc);
create index person_userid_iap_pk on person_userid_iap ( person_userid_iap_key asc);
create index phones_01_idx on phones ( group_id asc, data_type_key asc, person_id asc, end_date asc);
create index phones_02_idx on phones ( data_type_key asc, person_id asc, start_date asc);
create index phones_10_idx on phones ( person_id asc);
create index phones_20_idx on phones ( data_type_key asc);
create index phones_pk on phones ( phone_key asc);
create index photo_id_issuer_pk on photo_id_issuer ( photo_id_issuer_key asc);
create index photo_id_issuer_data_type_pk on photo_id_issuer_data_type ( photo_id_issuer_data_type_key asc);
create index policies_pk on policies ( policy_key asc);
create index psu_directory_01_idx on psu_directory ( person_id asc, end_date asc);
create index psu_directory_10_idx on psu_directory ( person_id asc);
create index psu_directory_20_idx on psu_directory ( userid asc, person_id asc);
create index psu_directory_pk on psu_directory ( psu_directory_key asc);
create index psu_id_01_idx on psu_id ( person_id asc, end_date asc);
create index psu_id_02_idx on psu_id ( psu_id asc, end_date asc);
create index psu_id_pk on psu_id ( psu_id_key asc);
create index psu_id_exceptions_idx on psu_id_exceptions ( psu_id asc);
create index ra_affiliation_01_idx on ra_affiliation ( affiliation_key asc, registration_authority_key asc);
create index ra_affiliation_pk on ra_affiliation ( ra_affiliation_key asc);
create index ra_applications_pk on ra_applications ( ra_application_key asc);
create index ra_application_properites_pk on ra_application_properties ( ra_application_properties_key asc);
create index ra_comments_pk on ra_comments ( ra_comment_key asc);
create index ra_iap_assign_pk on ra_iap_assign ( ra_iap_assign_key asc);
create index ra_screens_pk on ra_screens ( ra_screen_key asc);
create index ra_screen_fields_pk on ra_screen_fields ( ra_screen_field_key asc);
create index ra_server_principals_01_idx on ra_server_principals ( ra_server_principal asc, end_date asc, registration_authority_key asc);
create index ra_server_principals_pk on ra_server_principals ( ra_server_principal_key asc);
create index registration_authority_01_idx on registration_authority ( end_date asc);
create index registration_authority_02_idx on registration_authority ( registration_authority asc);
create index registration_authority_10_idx on registration_authority ( ra_contact_key asc);
create index registration_authority_pk on registration_authority ( registration_authority_key asc);
create index security_questions_pk on security_questions ( sec_quest_key asc);
create index security_question_answers_pk on security_question_answers ( sec_quest_answer_key asc);
create index services_pk on services ( service_key asc);
create index service_log_01_idx on service_log ( request_userid asc);
create index service_log_pk on service_log ( service_log_key asc);
create index srvclog_request_start_idx on service_log ( request_start asc);
create index uiapp_pk on ui_applications ( ui_application_key asc);
create index ui_field_types_pk on ui_field_types ( ui_field_type asc);
create index ui_log_pk on ui_log ( ui_log_key asc);
create index ui_screens_pk on ui_screens ( ui_screen_name asc);
create index ui_screen_fields_pk on ui_screen_fields ( ui_screen_name asc, ui_field_name asc);
create index userid_02_idx on userid ( char_part asc, num_part asc);
create index userid_10_idx on userid ( person_id asc);
create index userid_pk on userid ( userid asc, person_id asc);
create index user_policy_status_pk on userid_policy_status ( userid_policy_status_key asc);
create index userid_pool_idx on userid_pool ( char_part asc, num_part asc);
create index userid_pool_pk on userid_pool ( userid_pool_key asc);
create index user_comments_01_idx on user_comments ( end_date asc, userid asc);
create index user_comments_20_idx on user_comments ( userid asc, person_id asc);
create index user_comments_21_idx on user_comments ( data_type_key asc);
create index user_comments_pk on user_comments ( user_comment_key asc);
create index web_service_01_idx on web_service ( web_service asc, end_date asc, web_service_key asc);
create index web_service_pk on web_service ( web_service_key asc);
create index web_service_access_pkx on web_service_access ( web_service_access_key asc);
create index wsa_websrvc_fkx on web_service_access ( web_service_key asc);
create index wsa_cag_fkx on web_service_access ( cpr_access_groups_key asc);
create index server_principal_ip_pkx on server_principal_ip ( server_principal_ip_key asc);
create index spi_raserverprinc_fkx on server_principal_ip ( ra_server_principal_key asc);
create unique index cpr_access_groups_pkx on cpr_access_groups ( cpr_access_groups_key asc);
create unique index cag_spkey_group_ukx on cpr_access_groups ( ra_server_principal_key asc , access_group asc);
create index cag_rasrvrprinc_fkx on cpr_access_groups ( ra_server_principal_key asc);
create unique index message_consumer_pkX on message_consumer ( message_consumer_key asc );
create unique index mec_consumer_UKX on message_consumer ( consumer asc );
create unique index msg_con_mapping_pkX on message_consumer_mapping ( msg_con_mapping_key asc );
create index mcm_mec_fkX on message_consumer_mapping ( message_consumer_key asc );
create index mcm_services_fkX on message_consumer_mapping ( service_key asc );
create index mcm_websrvc_fkX on message_consumer_mapping ( web_service_key asc );
create unique index user_service_status_pkX on user_service_status ( user_service_status_key asc );
create index usersrvcstat_userid_fkX on user_service_status ( userid asc , person_id asc );
create index usersrvcstat_services_fkX on user_service_status ( service_key asc );
create index usersrvcstat_mec_fkX on user_service_status ( message_consumer_key asc );
create unique index mc_warning_notifications_pkX on mc_warning_notifications ( mc_warning_notification_key asc );
create index mcw_mec_fkX on mc_warning_notifications ( message_consumer_key asc );
create index mcw_eventtrig_fkX on mc_warning_notifications ( event_trigger_key asc );
create unique index change_notification_types_pkX on change_notification_types ( change_notification_types_key asc );
create unique index cnt_notification_type_UKX on change_notification_types ( notification_type asc );
create unique index change_notifications_pkX on change_notifications ( change_notifications_key asc );
create unique index cno_consumer_chngnottype_UKX on change_notifications ( message_consumer_key asc , change_notification_types_key asc );
create index cno_mec_fkX on change_notifications ( message_consumer_key asc );
create index cno_cnt_fkX on change_notifications ( change_notification_types_key asc );
create unique index message_log_pkX on message_log ( message_log_key asc );
create index msglog_websrvc_fkX on message_log ( web_service_key asc );
create index msglog_mec_fkX on message_log ( message_consumer_key asc );
create index MESSAGE_LOG_01_IDX on message_log ( created_on asc );
create index message_log_history_01_IDX on message_log_history ( message_id asc );
-- end of indexes --

