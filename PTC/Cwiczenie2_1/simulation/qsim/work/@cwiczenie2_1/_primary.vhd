library verilog;
use verilog.vl_types.all;
entity Cwiczenie2_1 is
    port(
        oLEDG           : out    vl_logic_vector(2 downto 0);
        iSW             : in     vl_logic_vector(1 downto 0)
    );
end Cwiczenie2_1;
