library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_5 is
    port(
        a               : in     vl_logic_vector(3 downto 0);
        b               : in     vl_logic_vector(3 downto 0);
        w               : out    vl_logic_vector(7 downto 0);
        clk             : in     vl_logic
    );
end Cwiczenie5_5;
