library verilog;
use verilog.vl_types.all;
entity Cwiczenie4 is
    port(
        iSW             : in     vl_logic_vector(17 downto 0);
        oHEX0_D         : out    vl_logic_vector(0 to 6);
        oHEX1_D         : out    vl_logic_vector(0 to 6);
        oHEX2_D         : out    vl_logic_vector(0 to 6);
        oHEX3_D         : out    vl_logic_vector(0 to 6);
        oHEX4_D         : out    vl_logic_vector(0 to 6);
        oHEX5_D         : out    vl_logic_vector(0 to 6);
        oHEX6_D         : out    vl_logic_vector(0 to 6)
    );
end Cwiczenie4;
