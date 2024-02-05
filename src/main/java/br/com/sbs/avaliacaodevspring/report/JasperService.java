package br.com.sbs.avaliacaodevspring.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    private static final String JASPER_DIRECTORY = "classpath:jasper/";
    private static final String JASPER_PREFIX = "employee-exam-";
    private static final String JASPER_SUFFIX = ".jasper";

    private final Connection connection;

    private Map<String, Object> params = new HashMap<>();

    public JasperService(Connection connection) {
        this.connection = connection;
    }

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public byte[] exportPDF(String code) {
        byte [] bytes = null;
        try {
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(JASPER_PREFIX).concat(code).concat(JASPER_SUFFIX));
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
