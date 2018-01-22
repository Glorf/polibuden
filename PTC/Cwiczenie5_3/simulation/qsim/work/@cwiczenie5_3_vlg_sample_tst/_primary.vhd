library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_3_vlg_sample_tst is
    port(
        clk             : in     vl_logic;
        in1             : in     vl_logic_vector(0 to 7);
        in2             : in     vl_logic_vector(0 to 7);
        sampler_tx      : out    vl_logic
    );
end Cwiczenie5_3_vlg_sample_tst;
