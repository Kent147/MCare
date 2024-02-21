package vn.mcare.system.common.helper;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;

@Slf4j
public class PdfHelper {

  public static void createPdf(String path, java.util.List<PrescriptionDto> prescriptions) {
    try {

      Document document = new Document();

      File file = new File(path);
      file.createNewFile();
      PdfWriter.getInstance(document, new FileOutputStream(file));

      document.open();
      document.add(image());
      document.add(header());
      document.add(headerbody());
      document.add(bodyPrescription(prescriptions));
      document.add(footer("Vuong Minh Thang"));
      document.close();

    } catch (IOException | DocumentException e) {
      log.error(e.getMessage());
    }
  }

  public static void createPdf(String path, String cif, String fullName, String gender, String address, String bod, String description) {
    try {

      Document document = new Document();

      File file = new File(path);
      file.createNewFile();
      PdfWriter.getInstance(document, new FileOutputStream(file));

      document.open();
      document.add(image());
      document.add(header());
      document.add(headerbody());
      document.add(hbody(cif, fullName, gender, address, bod, description));
      document.add(footer(fullName));
      document.close();

    } catch (IOException | DocumentException e) {
      log.error(e.getMessage());
    }
  }

  private static Element image() {
    try {
      Image image1 = Image.getInstance(System.getProperty("user.dir") + "\\src\\main\\resources\\h1.png");
      image1.setAbsolutePosition(70f, 725f);
      image1.scaleAbsolute(80, 80);
      return image1;
    } catch (IOException | BadElementException e) {
      log.error(e.getMessage());
    }
    return null;
  }

  private static Element header() {
    Font h1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    // create text
    Paragraph header = new Paragraph("MOMENT-CARE", h1);
    header.setAlignment(Element.ALIGN_CENTER);
    return header;
  }

  private static Element headerbody() {
    Font hb = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);
    Font h1 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    // text
    Paragraph headerbody = new Paragraph(
            "TEL : (84) 0123-456-789 \t - \t EMAIL : thangvtmps09070@fpt.edu.vn \n"
                    + " Address : 64, An Nhon street, 17 ward, Go Vap district, Ho Chi Minh City, Viet Nam",
            hb);
    Paragraph header = new Paragraph("PRESCRIPTION", h1);
    header.add(new Paragraph());
    header.setAlignment(Element.ALIGN_CENTER);
    Chunk line = new Chunk(new LineSeparator());
    headerbody.setAlignment(Element.ALIGN_CENTER);
    List list = new List(12);
    list.setListSymbol("\u0020");
    list.add(new ListItem(headerbody));
    list.add(new ListItem(line));
    list.add(new ListItem(header));
    return list;
  }

  private static Element hbody(String cif, String fullName, String gender, String address, String bod, String description) {
    Font h1 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    // create text
    Paragraph header = new Paragraph("ID : " + cif + "\rFULLNAME : " + fullName + "\rBIRTHDAY : " + bod + "\rGENDER : " + gender + "\rADDRESS : " + address + "\rREASON : " + description, h1);
    header.setIndentationLeft(100);
    return header;
  }

  private static PdfPTable bodyPrescription(java.util.List<PrescriptionDto> prescriptions) {
    Font h1 = new Font(FontFamily.TIMES_ROMAN, 13, Font.BOLD);

    PdfPTable table = new PdfPTable(3);
    Phrase medicine = new Phrase("Medicine");
    Phrase amount = new Phrase("Amount");
    Phrase calUnit = new Phrase("Cal unit");

    medicine.setFont(h1);
    amount.setFont(h1);
    calUnit.setFont(h1);

    table.addCell(medicine);
    table.addCell(amount);
    table.addCell(calUnit);

    prescriptions.forEach(med -> {
      table.addCell(med.getMedicineName());
      table.addCell(med.getAmount().toString());
      table.addCell(med.getCalUnit());
    });

    table.setHeaderRows(0);
    return table;
  }

  private static Element footer(String fullName) {
    Chunk line = new Chunk(new LineSeparator());
    Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
    Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.ITALIC);
    Paragraph footer = new Paragraph(LocalDate.now().getDayOfWeek() + ",DAY " + LocalDate.now().getDayOfMonth() + ",MONTH " + LocalDate.now().getMonthValue() + ",YEAR " + LocalDate.now().getYear(), f1);
    Paragraph ft1 = new Paragraph("\n \n \n" + fullName, f2);
    footer.setAlignment(Element.ALIGN_RIGHT);
    ft1.setAlignment(Element.ALIGN_RIGHT);
    List list = new List(12);
    list.setListSymbol("\u0020");
    list.add(new ListItem(line));
    list.add(new ListItem(footer));
    list.add(new ListItem(ft1));
    return list;
  }
}

