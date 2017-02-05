//package io;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.kabeja.dxf.DXFConstants;
//import org.kabeja.dxf.DXFDocument;
//import org.kabeja.dxf.DXFLine;
//import org.kabeja.parser.DXFParser;
//import org.kabeja.parser.ParseException;
//import org.kabeja.parser.Parser;
//import org.kabeja.parser.ParserBuilder;
//
//public class DXFReader {
//
////	@SuppressWarnings({ "rawtypes" })
////	public static ArrayList<Line> getAutocadFile(String filePath) throws ParseException {
////		ArrayList<Line> lines = new ArrayList<>();
////		Parser parser = ParserBuilder.createDefaultParser();
////		parser.parse(filePath, DXFParser.DEFAULT_ENCODING);
////		DXFDocument doc = parser.getDocument();
////		List lst = doc.getDXFLayer("0").getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);
////		for (int index = 0; index < lst.size(); index++) {
////			DXFLine dxfline = (DXFLine) lst.get(index);
////			Line line = new Line(new Point(dxfline.getStartPoint().getX(), dxfline.getStartPoint().getY()),
////					new Point(dxfline.getEndPoint().getX(), dxfline.getEndPoint().getY()));
////			lines.add(line);
////		}
////		return lines;
////	}
//
//}
