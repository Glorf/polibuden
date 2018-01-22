library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_2 is
    port(
        a               : in     vl_logic_vector(0 to 7);
        b               : in     vl_logic_vector(0 to 7);
        cin             : in     vl_logic;
        clk             : in     vl_logic;
        sum             : out    vl_logic_vector(0 to 7);
        sout            : out    vl_logic
    );
end Cwiczenie5_2;
