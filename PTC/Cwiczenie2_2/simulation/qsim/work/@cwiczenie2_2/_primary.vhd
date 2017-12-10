library verilog;
use verilog.vl_types.all;
entity Cwiczenie2_2 is
    port(
        oLEDG           : out    vl_logic_vector(2 downto 0);
        iSW             : in     vl_logic_vector(2 downto 0)
    );
end Cwiczenie2_2;
