library verilog;
use verilog.vl_types.all;
entity Cwiczenie3_4 is
    port(
        oLEDG           : out    vl_logic_vector(7 downto 0);
        iSW             : in     vl_logic_vector(2 downto 0)
    );
end Cwiczenie3_4;
