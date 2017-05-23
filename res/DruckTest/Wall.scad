translate([0, 0, (10.75)/2]){
	scale([1.000, 1.000, 10.750]){
	difference(){
	rotate(0.00,[0,0,1]){
	cube([100.00,6.00,1.00], true);
}	translate([-50.00, -0.00, -7.50]){
	translate([0.00, 0.00, 9.50]){
	scale([1.000, 1.000, 11.000]){
	difference(){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}	rotate(0.00,[0,0,1]){
	translate([10.25, 0.00, 0.00]){
	cube([10.00,2.50,4.00], true);
}}	rotate(90.00,[0,0,1]){
	translate([10.25, 0.00, 0.00]){
	cube([10.00,2.50,4.00], true);
}}}}}}	translate([50.00, 0.00, -7.50]){
	translate([0.00, 0.00, 9.50]){
	scale([1.000, 1.000, 11.000]){
	difference(){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}	rotate(90.00,[0,0,1]){
	translate([10.25, 0.00, 0.00]){
	cube([10.00,2.50,4.00], true);
}}	rotate(180.00,[0,0,1]){
	translate([10.25, 0.00, 0.00]){
	cube([10.00,2.50,4.00], true);
}}}}}}}}}