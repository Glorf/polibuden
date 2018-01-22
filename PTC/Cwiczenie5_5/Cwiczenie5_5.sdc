## Generated SDC file "Cwiczenie5_5.sdc"

## Copyright (C) 1991-2013 Altera Corporation
## Your use of Altera Corporation's design tools, logic functions 
## and other software and tools, and its AMPP partner logic 
## functions, and any output files from any of the foregoing 
## (including device programming or simulation files), and any 
## associated documentation or information are expressly subject 
## to the terms and conditions of the Altera Program License 
## Subscription Agreement, Altera MegaCore Function License 
## Agreement, or other applicable license agreement, including, 
## without limitation, that your use is for the sole purpose of 
## programming logic devices manufactured by Altera and sold by 
## Altera or its authorized distributors.  Please refer to the 
## applicable agreement for further details.


## VENDOR  "Altera"
## PROGRAM "Quartus II"
## VERSION "Version 13.0.1 Build 232 06/12/2013 Service Pack 1 SJ Web Edition"

## DATE    "Tue Dec 19 14:11:11 2017"

##
## DEVICE  "EP2C70F896C6"
##


#**************************************************************
# Time Information
#**************************************************************

set_time_format -unit ns -decimal_places 3



#**************************************************************
# Create Clock
#**************************************************************

create_clock -name {zegar} -period 15.000 -waveform { 0.000 7.500 } [get_ports {KEY[0]}]


#**************************************************************
# Create Generated Clock
#**************************************************************



#**************************************************************
# Set Clock Latency
#**************************************************************



#**************************************************************
# Set Clock Uncertainty
#**************************************************************



#**************************************************************
# Set Input Delay
#**************************************************************

set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[0]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[1]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[2]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[3]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[4]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[5]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[6]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {iSW[7]}]


#**************************************************************
# Set Output Delay
#**************************************************************

set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX0[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX1[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX2[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX3[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX4[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX5[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX6[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {HEX7[6]}]


#**************************************************************
# Set Clock Groups
#**************************************************************



#**************************************************************
# Set False Path
#**************************************************************



#**************************************************************
# Set Multicycle Path
#**************************************************************



#**************************************************************
# Set Maximum Delay
#**************************************************************



#**************************************************************
# Set Minimum Delay
#**************************************************************



#**************************************************************
# Set Input Transition
#**************************************************************

