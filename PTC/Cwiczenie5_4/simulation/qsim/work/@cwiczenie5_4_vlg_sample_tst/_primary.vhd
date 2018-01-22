library verilog;
use verilog.vl_types.all;
entity Cwiczenie5_4_vlg_sample_tst is
    port(
        iSW             : in     vl_logic_vector(17 downto 0);
        KEY             : in     vl_logic_vector(0 downto 0);
        sampler_tx      : out    vl_logic
    );
end Cwiczenie5_4_vlg_sample_tst;
