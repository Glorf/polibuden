library verilog;
use verilog.vl_types.all;
entity Cwiczenie4_vlg_check_tst is
    port(
        oHEX0_D         : in     vl_logic_vector(0 to 6);
        oHEX1_D         : in     vl_logic_vector(0 to 6);
        oHEX2_D         : in     vl_logic_vector(0 to 6);
        oHEX3_D         : in     vl_logic_vector(0 to 6);
        oHEX4_D         : in     vl_logic_vector(0 to 6);
        oHEX5_D         : in     vl_logic_vector(0 to 6);
        oHEX6_D         : in     vl_logic_vector(0 to 6);
        sampler_rx      : in     vl_logic
    );
end Cwiczenie4_vlg_check_tst;
