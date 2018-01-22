library ieee;
use ieee.std_logic_1164.all;

entity mul4bit is
	Port(
		a: in std_logic_vector(3 downto 0);
		b: in std_logic_vector(3 downto 0);
		w: out std_logic_vector(7 downto 0);
		clk: in std_logic
	);
end mul4bit;

architecture sumatory of mul4bit is
component mul4bitpart
	Port(
		a: in std_logic_vector(3 downto 0);
		b: in std_logic;
		s: in std_logic_vector(3 downto 0);
		cin: in std_logic;
		w: out std_logic_vector(3 downto 0);
		cout: out std_logic
	);
end component;
component rejestr8bit
	Port(
		clk: in std_logic;
		r: in std_logic_vector(7 downto 0);
		q: out std_logic_vector(7 downto 0)
	);
end component;
component rejestr4bit
	Port(
		clk: in std_logic;
		r: in std_logic_vector(3 downto 0);
		q: out std_logic_vector(3 downto 0)
	);
end component;
signal asignal, bsignal, s0, s1, s2, s3: std_logic_vector(3 downto 0);
signal outsignal: std_logic_vector(7 downto 0);
signal C12, C23, cout: std_logic;
begin
	rega: rejestr4bit port map(clk, a, asignal);
	regb: rejestr4bit port map(clk, b, bsignal);
	s0(0) <= asignal(0) and bsignal(0);
	s0(1) <= asignal(1) and bsignal(0);
	s0(2) <= asignal(2) and bsignal(0);
	s0(3) <= asignal(3) and bsignal(0);
	part1: mul4bitpart port map(asignal, bsignal(1), s0, '0', s1, C12);
	part2: mul4bitpart port map(asignal, bsignal(2), s1, C12, s2, C23);
	part3: mul4bitpart port map(asignal, bsignal(3), s2, C23, s3, cout);
	
	outsignal(0) <= s0(0);
	outsignal(1) <= s1(0);
	outsignal(2) <= s2(0);
	outsignal(6 downto 3) <= s3;
	outsignal(7) <= cout;
	
	regout: rejestr8bit port map(clk, outsignal, w);
end sumatory;