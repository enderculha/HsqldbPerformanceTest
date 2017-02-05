package com.hsqldb;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hsqldb.Server;

public class Constants {

    public static Server       hsqlServer;
    public static String       url       = "jdbc:hsqldb:hsql://localhost/test;hsqldb.tx=mvcc";

    public static String       mccMnc    = "90505";

    public static long         bnumber   = 905050000000L;

    public static final String create    = "CREATE MEMORY TABLE xdr_iucs ("
                                                 + "  start_time datetime NOT NULL,"
                                                 + "  end_time datetime  NULL,"
                                                 + "  source_ip varchar(15)  NULL,"
                                                 + "  dest_ip varchar(15)  NULL,"
                                                 + "  opc smallint  NOT NULL,"
                                                 + "  dpc smallint  NOT NULL,"
                                                 + "  slr varchar(25)  NULL,"
                                                 + "  dlr varchar(25)  NULL,"
                                                 + "  protocol int  NULL,"
                                                 + "  a_number varchar(50)  NULL,"
                                                 + "  a_nature int  NULL,"
                                                 + "  b_number varchar(50)  NULL,"
                                                 + "  b_nature int  NULL,"
                                                 + "  direction int  NULL," + "  result int  NULL,"
                                                 + "  procedure_type tinyint NOT NULL,"
                                                 + "  serving_cell_or_sac_indicator int  NULL,"
                                                 + "  serving_cell_or_sac varchar(10)  NULL,"
                                                 + "  serving_lac varchar(25)  NULL,"
                                                 + "  imsi varchar(25)  NULL,"
                                                 + "  imeisv varchar(25)  NULL,"
                                                 + "  tmsi varchar(25)  NULL,"
                                                 + "  app_part_cause int  NULL,"
                                                 + "  sccp_cause int  NULL,"
                                                 + "  mm_reject_cause int  NULL,"
                                                 + "  rr_cause int  NULL,"
                                                 + "  mcc varchar(10)  NULL,"
                                                 + "  mnc varchar(10)  NULL,"
                                                 + "  alert_duration int  NULL,"
                                                 + "  conversation_duration int  NULL,"
                                                 + "  setup_duration int  NULL,"
                                                 + "  call_connected tinyint  NULL,"
                                                 + "  itc int  NULL,"
                                                 + "  time_adv_info int  NULL,"
                                                 + "  release_origin int  NULL,"
                                                 + "  cc_cause int  NULL,"
                                                 + "  cc_cause2 int  NULL,"
                                                 + "  app_part_cause_2 int  NULL,"
                                                 + "  handover_type tinyint  NULL,"
                                                 + "  handover_cause tinyint  NULL,"
                                                 + "  handover_leg tinyint  NULL,"
                                                 + "  last_target_lac varchar(25)  NULL,"
                                                 + "  last_target_cell varchar(10)  NULL,"
                                                 + "  previous_target_lac varchar(25)  NULL,"
                                                 + "  previous_target_cell varchar(10)  NULL,"
                                                 + "  ho_ref varchar(25)  NULL,"
                                                 + "  ho_ref_target varchar(25)  NULL,"
                                                 + "  cell_description int  NULL,"
                                                 + "  cell_description_target int  NULL,"
                                                 + "  a_present tinyint  NULL,"
                                                 + "  alerted smallint   NULL,"
                                                 + "  ringduration int  NULL,"
                                                 + "  sms_status int  NULL,"
                                                 + "  smsc varchar(25)  NULL,"
                                                 + "  cp_error int  NULL," + "  rp_mti int  NULL,"
                                                 + "  rp_cause int  NULL,"
                                                 + "  tp_status int  NULL,"
                                                 + "  tp_failure_cause int  NULL,"
                                                 + "  tp_alphabet int  NULL,"
                                                 + "  tp_mti int  NULL," + "  tp_sr int  NULL,"
                                                 + "  concat_ref int  NULL,"
                                                 + "  concat_msg_count int  NULL,"
                                                 + "  concat_msg_number int  NULL,"
                                                 + "  data_length int  NULL,"
                                                 + "  smsc_timestamp datetime  NULL,"
                                                 + "  smms_arrival_timestamp datetime  NULL,"
                                                 + "  origin smallint   NULL,"
                                                 + "  origin_indicator smallint   NULL,"
                                                 + "  location_update_type smallint   NULL,"
                                                 + "  location_requested tinyint  NULL,"
                                                 + "  paging_cause int  NULL,"
                                                 + "  reversed_a_number varchar(50)  NULL,"
                                                 + "  reversed_b_number varchar(50)  NULL,"
                                                 + "  first_setup tinyint  NULL,"
                                                 + "  ho_performed_count smallint   NULL,"
                                                 + "  handover_cause_2 tinyint  NULL,"
                                                 + "  ho_ref_failed_target varchar(25)  NULL,"
                                                 + "  cell_description_failed_target int  NULL,"
                                                 + "  user_1 int  NULL," + "  user_2 int  NULL,"
                                                 + "  user_3 varchar(25)  NULL,"
                                                 + "  user_4 varchar(25)  NULL,"
                                                 + "  kpi_bit_set_0_0 bit(64)  NULL,"
                                                 + "  kpi_bit_set_0_1 bit(64)  NULL,"
                                                 + "  kpi_bit_set_1_0 bit(64)  NULL,"
                                                 + "  kpi_bit_set_1_1 bit(64)  NULL,"
                                                 + "  start_time_ms smallint NOT NULL,"
                                                 + "  end_time_ms smallint NOT NULL,"
                                                 + "  trace_file_name varchar(50)  NULL,"
                                                 + "  trace_offset int  NULL,"
                                                 + "  trace_len int  NULL,"
                                                 + "  partition_id smallint  NULL,"
                                                 + "  probe_id tinyint NOT NULL,"
                                                 + "  probe_instance tinyint NOT NULL);";

    //public static String       setType   = "SET DATABASE DEFAULT TABLE TYPE { CACHED };";

    public static final String insert    = "INSERT INTO xdr_iucs (start_time, end_time, source_ip, dest_ip, opc, dpc, slr, dlr, protocol, a_number, a_nature, b_number, b_nature, direction, result, procedure_type, serving_cell_or_sac_indicator, serving_cell_or_sac, serving_lac, imsi, imeisv, tmsi, app_part_cause, sccp_cause, mm_reject_cause, rr_cause, mcc, mnc, alert_duration, conversation_duration, setup_duration, call_connected, itc, time_adv_info, release_origin, cc_cause, cc_cause2, app_part_cause_2, handover_type, handover_cause, handover_leg, last_target_lac, last_target_cell, previous_target_lac, previous_target_cell, ho_ref, ho_ref_target, cell_description, cell_description_target, a_present, alerted, ringduration, sms_status, smsc, cp_error, rp_mti, rp_cause, tp_status, tp_failure_cause, tp_alphabet, tp_mti, tp_sr, concat_ref, concat_msg_count, concat_msg_number, data_length, smsc_timestamp, smms_arrival_timestamp, origin, origin_indicator, location_update_type, location_requested, paging_cause, reversed_a_number, reversed_b_number, first_setup, ho_performed_count, handover_cause_2, ho_ref_failed_target, cell_description_failed_target, user_1, user_2, user_3, user_4, kpi_bit_set_0_0, kpi_bit_set_0_1, kpi_bit_set_1_0, kpi_bit_set_1_1, start_time_ms, end_time_ms, trace_file_name, trace_offset, trace_len, partition_id, probe_id, probe_instance) VALUES ('2014-05-21 08:39:27', '2014-05-21 08:40:30', '10.212.170.254', '10.102.14.142', 5539, 2000, '12293897', '9621992', 1, NULL, NULL, '905078127926', 1, 1, 1, 2, 1, '29756', '42701', '286034090900929', NULL, '58F9739F', 11, 3, 0, NULL, '286', '03', 3161, 56243, 270, 1, 0, NULL, 1, NULL, NULL, NULL, 0, 43, 1, '32703', '29751', NULL, NULL, '103', '103', 47883, 47883, NULL, 1, 2846, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '629721870509', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'00000100', b'00000000', b'00000100', b'00000000', 611, 953, '16062014/d_2_0_6_1.bin', 0, 2930, 2108, 2, 0);";
    public static String       row;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /* = "('"
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              + Get + "2014-05-21 08:39:27.000000" +"'"
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               + ",'2014-05-21 08:40:30.000000','10.212.170.254','10.102.14.142',5539,2000,'12293897','9621992',1,NULL,NULL,'"
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               +bnumber+
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               "',1,1,1,2,1,'29756','42701','286034090900929',NULL,'58F9739F',11,3,0,NULL,'286','03',3161,56243,270,1,0,NULL,1,NULL,NULL,NULL,0,43,1,'32703','29751',NULL,NULL,'103','103',47883,47883,NULL,1,2846,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'629721870509',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0000010000000000000000000000000000000000000000000000000000000000','0000000000000000000000000000000000000000000000000000000000000000','0000010000000000000000000000000000000000000000000000000000000000','0000000000000000000000000000000000000000000000000000000000000000',611,953,'16062014/d_2_0_6_1.bin',0,2930,2108,2,0)";
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             */

    public static final String index1    = "create index starttime on xdr_iucs(start_time); ";
    public static final String index2    = "create index b on xdr_iucs(b_number); ";
    public static final String index3    = "create index starttime_imsi on xdr_iucs(b_number, start_time); ";
    public static final String sql_start = "INSERT INTO xdr_iucs VALUES ";

    public static void updateRow() {

        row = "('"
                + GetFormattedDateNow()
                + "'"
                + ",'2014-05-21 08:40:30.000000','10.212.170.254','10.102.14.142',5539,2000,'12293897','9621992',1,NULL,NULL,'"
                + bnumber
                + "',1,1,1,2,1,'29756','42701','286034090900929',NULL,'58F9739F',11,3,0,NULL,'286','03',3161,56243,270,1,0,NULL,1,NULL,NULL,NULL,0,43,1,'32703','29751',NULL,NULL,'103','103',47883,47883,NULL,1,2846,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'629721870509',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0000010000000000000000000000000000000000000000000000000000000000','0000000000000000000000000000000000000000000000000000000000000000','0000010000000000000000000000000000000000000000000000000000000000','0000000000000000000000000000000000000000000000000000000000000000',611,953,'16062014/d_2_0_6_1.bin',0,2930,2108,2,0)";
        bnumber = bnumber + 1; // increment msisdn by 1.
    }

    public static String GetFormattedDateNow() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return ft.format(dNow);
    }

}
