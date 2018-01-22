library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_2_vlg_sample_tst is
    port(
        a               : in     vl_logic_vector(0 to 7);
        b               : in     vl_logic_vector(0 to 7);
        cin             : in     vl_logic;
        clk             : in     vl_logic;
        sampler_tx      : out    vl_logic
    );
end Cwiczenie5_2_vlg_sample_tst;
