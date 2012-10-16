
drop database cpr;

create database cpr;

use cpr;

    drop table if exists identifier_type;

    drop table if exists person_identifier;

    drop table if exists addresses;

    drop table if exists aff_transition_rules;

    drop table if exists affiliations;

    drop table if exists allowed_char_part;

    drop table if exists allowed_num_part;

    drop table if exists application_properties;

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

    drop table if exists group_access;

    drop table if exists group_data_type_access;

    drop table if exists group_member_comments;

    drop table if exists group_members;

    drop table if exists iam_groups;

    drop table if exists iap;

    drop table if exists iap_data_element;

    drop table if exists iap_document;

    drop table if exists iap_ext_mapping;

    drop table if exists iap_rules;

    drop table if exists id_card_print_log;

    drop table if exists java_exceptions;

    drop table if exists match_results;

    drop table if exists message_log;

    drop table if exists message_log_history;

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

    drop table if exists ra_groups;

    drop table if exists ra_iap_assign;

    drop table if exists ra_screen_fields;

    drop table if exists ra_screens;

    drop table if exists ra_server_principals;

    drop table if exists ra_ui_application;

    drop table if exists registration_authority;

    drop table if exists security_question_answers;

    drop table if exists security_questions;

    drop table if exists service_actions;

    drop table if exists service_log;

    drop table if exists service_provisioner;

    drop table if exists services;

    drop table if exists sp_notification;

    drop table if exists sp_response;

    drop table if exists sp_services;

    drop table if exists sp_warning_notifications;

    drop table if exists ui_screen_fields;

    drop table if exists ui_screens;

    drop table if exists user_comments;

    drop table if exists user_service_status;

    drop table if exists userid;

    drop table if exists userid_policy_status;

    drop table if exists userid_pool;

    drop table if exists usps_state_types;

    drop table if exists usps_states;

    drop table if exists web_service;

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

    create table application_properties (
        application_properties_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        key_name varchar(200) not null,
        key_value varchar(200) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        ra_ui_application_key bigint not null,
        primary key (application_properties_key)
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

    create table group_access (
        group_access_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        ra_group_key bigint not null,
        start_date datetime not null,
        suspend_flag varchar(1) not null,
        web_service_key bigint not null,
        primary key (group_access_key)
    ) ENGINE=INNODB;

    create table group_data_type_access (
        group_data_type_access_key bigint not null auto_increment,
        archive_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        data_type_key bigint not null,
        iam_group_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        read_flag varchar(1) not null,
        write_flag varchar(1) not null,
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

    create table group_members (
        group_member_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iam_group_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint,
        start_date datetime not null,
        suspend_flag varchar(1) not null,
        userid varchar(30),
        primary key (group_member_key)
    ) ENGINE=INNODB;

    create table iam_groups (
        iam_group_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        iam_group varchar(60) not null,
        iam_group_desc varchar(100) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        parent_iam_group_key bigint,
        suspend_flag varchar(1) not null,
        primary key (iam_group_key)
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

    create table message_log (
        message_log_key bigint not null auto_increment,
        created_on datetime not null,
        last_update_on datetime not null,
        message_sent varchar(1000),
        number_of_tries bigint not null,
        request_userid varchar(30) not null,
        service_provisioner_key bigint not null,
        success_flag varchar(1),
        web_service_key bigint not null,
        primary key (message_log_key)
    ) ENGINE=INNODB;

    create table message_log_history (
        message_log_history_key bigint not null auto_increment,
        created_on datetime not null,
        error_code varchar(100),
        error_message varchar(1000),
        last_update_on datetime not null,
        message_id varchar(100),
        message_log_key bigint not null,
        message_received varchar(1000),
        message_received_timestamp datetime,
        message_sent_timestamp datetime not null,
        try_number bigint,
        primary key (message_log_history_key)
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

    create table ra_groups (
        ra_group_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        iam_group_key bigint not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        registration_authority_key bigint not null,
        start_date datetime,
        primary key (ra_group_key)
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

    create table ra_screen_fields (
        ra_screen_field_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        display_flag varchar(1) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        required_flag varchar(1) not null,
        ui_screen_field_key bigint not null,
        primary key (ra_screen_field_key)
    ) ENGINE=INNODB;

    create table ra_screens (
        ra_screen_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30),
        last_update_on datetime not null,
        ra_screen_order bigint not null,
        ra_ui_application_key bigint not null,
        ui_screen_key bigint not null,
        primary key (ra_screen_key)
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
        primary key (registration_authority_key)
    ) ENGINE=INNODB;

    create table security_question_answers (
        sec_quest_answer_key bigint not null auto_increment,
        answer varchar(100) not null,
        created_on datetime not null,
        person_id bigint not null,
        sec_quest_group_key bigint not null,
        sec_quest_key bigint not null,
        userid varchar(30) not null,
        primary key (sec_quest_answer_key)
    ) ENGINE=INNODB;

    create table security_questions (
        sec_quest_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        question varchar(100) not null,
        sec_quest_group_key bigint not null,
        start_date datetime not null,
        primary key (sec_quest_key)
    ) ENGINE=INNODB;

    create table service_actions (
        service_action_key bigint not null auto_increment,
        active_flag varchar(1) not null,
        created_by varchar(30) not null,
        created_on datetime not null,
        enum_string varchar(30) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        service_action varchar(30) not null,
        primary key (service_action_key)
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

    create table service_provisioner (
        service_provisioner_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        maximum_failure bigint,
        maximum_queue_size bigint,
        maximum_retries bigint,
        retry_interval bigint,
        service_provisioner varchar(50) not null,
        service_provisioner_email varchar(256),
        service_provisioner_queue varchar(128),
        service_provisioner_reg_date datetime not null,
        start_date datetime not null,
        suspend_flag varchar(1) not null,
        primary key (service_provisioner_key)
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

    create table sp_notification (
        sp_notification_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        service_provisioner_key bigint not null,
        start_date datetime not null,
        web_service_key bigint not null,
        primary key (sp_notification_key)
    ) ENGINE=INNODB;

    create table sp_response (
        sp_response_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        service_action_key bigint not null,
        service_key bigint not null,
        web_service_key bigint not null,
        primary key (sp_response_key)
    ) ENGINE=INNODB;

    create table sp_services (
        sp_service_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        end_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        max_days_provisioned bigint,
        service_key bigint not null,
        service_provisioner_key bigint not null,
        start_date datetime not null,
        primary key (sp_service_key)
    ) ENGINE=INNODB;

    create table sp_warning_notifications (
        sp_warning_notification_key bigint not null,
        end_date datetime,
        entry_timestamp datetime not null,
        event_trigger_key bigint not null,
        service_provisioner_key bigint not null,
        start_date datetime not null,
        primary key (sp_warning_notification_key)
    ) ENGINE=INNODB;

    create table ui_screen_fields (
        ui_screen_field_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        display_flag varchar(1) not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        required_flag varchar(1) not null,
        ui_field_name varchar(30) not null,
        ui_screen_key bigint not null,
        primary key (ui_screen_field_key)
    ) ENGINE=INNODB;

    create table ui_screens (
        ui_screen_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        required_flag varchar(1) not null,
        ui_screen_name varchar(30) not null,
        primary key (ui_screen_key)
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

    create table user_service_status (
        user_service_status_key bigint not null auto_increment,
        created_by varchar(30) not null,
        created_on datetime not null,
        deprovision_date datetime,
        expiration_date datetime,
        last_update_by varchar(30) not null,
        last_update_on datetime not null,
        person_id bigint not null,
        provision_date datetime not null,
        service_key bigint not null,
        service_provisioner_key bigint not null,
        userid varchar(30) not null,
        primary key (user_service_status_key)
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
alter table group_access add foreign key (web_service_key) references web_service(web_service_key);
alter table group_access add foreign key (ra_group_key) references ra_groups(ra_group_key);
alter table group_data_type_access add foreign key (data_type_key) references data_types(data_type_key);
alter table group_data_type_access add foreign key (iam_group_key) references iam_groups(iam_group_key);
alter table group_member_comments add foreign key (group_member_key) references group_members(group_member_key);
alter table group_members add foreign key (iam_group_key) references iam_groups(iam_group_key);
alter table id_card_print_log add foreign key (person_id_card_key) references person_id_card(person_id_card_key);
alter table match_results add foreign key (person_id) references person(person_id);
alter table message_log add foreign key (web_service_key) references web_service(web_service_key);
alter table message_log add foreign key (service_provisioner_key) references service_provisioner(service_provisioner_key);
alter table message_log_history add foreign key (message_log_key) references message_log(message_log_key);
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
alter table ra_groups add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_iap_assign add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_server_principals add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table ra_ui_application add foreign key (registration_authority_key) references registration_authority(registration_authority_key);
alter table security_question_answers add foreign key (person_id) references person(person_id);
alter table security_question_answers add foreign key (userid) references userid(userid);
alter table service_log add foreign key (web_service_key) references web_service(web_service_key);
alter table sp_notification add foreign key (web_service_key) references web_service(web_service_key);
alter table sp_response add foreign key (web_service_key) references web_service(web_service_key);
alter table user_comments add foreign key (person_id) references person(person_id);
alter table user_comments add foreign key (userid) references userid(userid);
alter table user_comments add foreign key (data_type_key) references data_types(data_type_key);
alter table user_service_status add foreign key (person_id) references person(person_id);
alter table userid add foreign key (person_id) references person(person_id);
alter table userid_policy_status add foreign key (person_id) references person(person_id);
alter table userid_policy_status add foreign key (userid) references userid(userid);
