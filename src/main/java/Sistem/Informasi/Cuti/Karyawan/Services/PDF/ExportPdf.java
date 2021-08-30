package Sistem.Informasi.Cuti.Karyawan.Services.PDF;

import Sistem.Informasi.Cuti.Karyawan.Model.Entity.DetailCuti;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class ExportPdf {

    public DetailCuti detailCuti;

    public ExportPdf(DetailCuti detailCuti){
        this.detailCuti=detailCuti;
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("FORMULIR PENGAJUAN CUTI KARYAWAN",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        Font font1 = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font1.setSize(11);
        font1.setColor(Color.BLACK);

        Paragraph j = new Paragraph("PT Pasim Utama Jln.Dakota No.8A Sukaraja,Cicendo,Bandung",font1);
        j.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(j);

        Font space = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        space.setSize(40);
        space.setColor(Color.BLACK);

        Paragraph spasi = new Paragraph(" ",space);
        document.add(spasi);

        Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font2.setSize(14);
        font2.setColor(Color.BLACK);

        Paragraph p1 = new Paragraph("NAMA                            :  "+detailCuti.getPengajuanCuti().getEmployee().getNama()+"",font2);
        p1.setAlignment(Paragraph.ALIGN_BASELINE);
        document.add(p1);

        Paragraph p2 = new Paragraph("NIP                                  :  "+detailCuti.getPengajuanCuti().getEmployee().getNip()+"",font2);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p2);

        Paragraph p3 = new Paragraph("DIVISI                            :  "+detailCuti.getPengajuanCuti().getEmployee().getDivisi()+"",font2);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p3);

        Paragraph p4 = new Paragraph("NO TELP                        :  "+detailCuti.getPengajuanCuti().getNoTelp()+"",font2);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p4);

        Paragraph p5 = new Paragraph("ALAMAT                       :  "+detailCuti.getPengajuanCuti().getAlamat()+"",font2);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p5);

        Paragraph p6 = new Paragraph("TANGGAL CUTI          :  "+detailCuti.getTanggal().toString()+"",font2);
        p6.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p6);

        Paragraph p7 = new Paragraph("LAMA CUTI                  :  "+detailCuti.getPengajuanCuti().getLama_cuti()+" hari",font2);
        p7.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p7);

        Paragraph p8 = new Paragraph("KETERANGAN             :  "+detailCuti.getPengajuanCuti().getKeterangan(),font2);
        p8.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p8);

        Font space2 = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        space2.setSize(100);
        space2.setColor(Color.BLACK);

        Paragraph spasi2 = new Paragraph(" ",space2);
        document.add(spasi2);

        Paragraph p10 = new Paragraph("(............................)                                                                  (..........................)\n" +
                "Mengetahui Karyawan                                                               Mengetahui HRD",font2);
        p10.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p10);

        document.close();

    }
}
