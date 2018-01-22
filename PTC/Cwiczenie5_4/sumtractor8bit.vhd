library ieee;
use ieee.std_logic_1164.all;

entity sumtractor8bit is
	Port(
		ain: in std_logic_vector(7 downto 0);
		bin: in std_logic_vector(7 downto 0);
		sel: in std_logic;
		clk: in std_logic;
		addsub: in std_logic;
		zout: out std_logic_vector(7 downto 0);
		overflow: out std_logic
	);
end sumtractor8bit;

architecture sumatory of sumtractor8bit is
component mydff
	Port(
		d, clk : in std_logic;
		q : out std_logic
	);
end component;
component rejestr8bit
	Port(
		clk: in std_logic;
		r: in std_logic_vector(7 downto 0);
		q: out std_logic_vector(7 downto 0)
	);
end component;
component sumator8bit
	Port(
		in1: in std_logic_vector(7 downto 0);
		in2: in std_logic_vector(7 downto 0);
		cin: in std_logic;
		sout: out std_logic_vector(7 downto 0);
		cout: out std_logic
	);
end component;
component sourceselectmux
	Port(
		ain: in std_logic_vector(7 downto 0);
		zin: in std_logic_vector(7 downto 0);
		mout: out std_logic_vector(7 downto 0);
		source: in std_logic
	);
end component;
signal ainsig, binsig: std_logic_vector(7 downto 0);
signal selsig, addsubsig, coutsig, overflowsig: std_logic;
signal bprocesssig: std_logic_vector(7 downto 0);
signal aprocesssig: std_logic_vector(7 downto 0);
signal prefinalzsig, finalzsig: std_logic_vector(7 downto 0);
begin
	-- REJESTRY WEJŚĆ
	ainreg: rejestr8bit port map(clk, ain, ainsig);
	binreg: rejestr8bit port map(clk, bin, binsig);
	selreg: mydff port map(sel, clk, selsig);
	addsubreg: mydff port map(addsub, clk, addsubsig);
	
	-- XORY
	bprocesssig(0) <= binsig(0) xor addsubsig;
	bprocesssig(1) <= binsig(1) xor addsubsig;
	bprocesssig(2) <= binsig(2) xor addsubsig;
	bprocesssig(3) <= binsig(3) xor addsubsig;
	bprocesssig(4) <= binsig(4) xor addsubsig;
	bprocesssig(5) <= binsig(5) xor addsubsig;
	bprocesssig(6) <= binsig(6) xor addsubsig;
	bprocesssig(7) <= binsig(7) xor addsubsig;
	
	sumsourcemux: sourceselectmux port map(ainsig, finalzsig, aprocesssig, selsig);
		
	sumator: sumator8bit port map(aprocesssig, bprocesssig, addsubsig, prefinalzsig, coutsig);
	outreg: rejestr8bit port map(clk, prefinalzsig, finalzsig);
		
	overflowsig <= (((coutsig xor prefinalzsig(7)) xor aprocesssig(7)) xor bprocesssig(7));
	overflowreg: mydff port map(overflowsig, clk, overflow);
	
	zout <= finalzsig;

end sumatory;