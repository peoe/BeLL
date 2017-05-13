package io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLine;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import graph.Vector;
import graph.Line;

public class DXFReader {

	// reading the .dxf file
	/**
	 * Reads the file specifies by the filepath and return a ArrayList of all
	 * Lines found in the file.
	 * 
	 * @param filePath
	 *            the filepath of the file
	 * @return an ArrayList of all Lines
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<DXFLine> getAutocadFile(String filePath) throws ParseException {
	

		// parsing the file to the document
		Parser parser = ParserBuilder.createDefaultParser();
		parser.parse(filePath, DXFParser.DEFAULT_ENCODING);

		DXFDocument doc = parser.getDocument();

		// extracting all DXFLines from the file
		
		List dxflst =  doc.getDXFLayer("0").getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);
		ArrayList<DXFLine> returnList = new ArrayList<>();
		for(int index = 0; index < dxflst.size(); index++){
			returnList.add((DXFLine) dxflst.get(index));
		}
		return returnList;

//		// converting all DXFLines to usable Lines
//		for (int index = 0; index < lst.size(); index++) {
//			DXFLine dxfline = (DXFLine) lst.get(index);
//
//			Line v = new Line(
//					new Vector(round2(dxfline.getStartPoint().getX()), round2(dxfline.getStartPoint().getY())),
//					new Vector(round2(dxfline.getEndPoint().getX()), round2(dxfline.getEndPoint().getY())));
//
//			vcs.add(v);
//		}
	}

}
