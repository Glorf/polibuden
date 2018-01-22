library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_3 is
    port(
        in1             : in     vl_logic_vector(0 to 7);
        in2             : in     vl_logic_vector(0 to 7);
        clk             : in     vl_logic;
        out0            : out    vl_logic_vector(0 to 7);
        overf           : out    vl_logic
    );
end Cwiczenie5_3;
