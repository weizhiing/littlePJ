package PPTtoPDF;

import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.Graphics2D;  
import java.awt.geom.Rectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.UUID;  
  
import javax.imageio.ImageIO;  
  
import org.apache.poi.POIXMLDocument;  
import org.apache.poi.hslf.extractor.PowerPointExtractor;  
import org.apache.poi.hslf.usermodel.HSLFSlide;  
import org.apache.poi.hslf.usermodel.HSLFSlideShow;  
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;  
import org.apache.poi.hslf.usermodel.HSLFTextRun;  
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;  
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;  
import org.apache.poi.xslf.usermodel.XMLSlideShow;  
import org.apache.poi.xslf.usermodel.XSLFSlide;  
import org.apache.xmlbeans.XmlException;  
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;  
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;  
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;  
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;  
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;  
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;  
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;  
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;  
  
/** 
 * PPT转image工具类 
 * 使用的Apache poi-3.14的版本  依赖第三方jar包：poi-3.14-20160307.jar、poi-ooxml-3.14-20160307.jar、 
 *                                      poi-ooxml-schemas-3.14-20160307.jar、poi-scratchpad-3.14-20160307.jar、xmlbeans-2.6.0.jar 
 * @author yds  
 * @date 2017-03-22 
 * 
 */  
public class PPTtoImage {  
      
  
    /** 
     * 将PPTX 文件转换成image 
     * @param orignalPPTFileName  //PPTX文件路径 如：d:/demo/demo1.pptx 
     * @param targetImageFileDir //转换后的图片保存路径 如：d:/demo/pptxImg 
     * @param imageFormatNameString //图片转化的格式字符串 ，如："jpg"、"jpeg"、"bmp" "png" "gif" "tiff" 
     * @return Map<String,Object> 
     *  key: converReturnResult   类型：boolean 转化结果 true 代表转换成功，false 代表转换失败 
     *  key:imgNames              类型：List<String> 转换成功后图片的全部名称集合 
     *  注：获取“imgNames”图片名称集合时，请先判断“converReturnResult” 是否为true；如果有一张转换失败则为false 
     */  
    @SuppressWarnings("resource")  
    public static  Map<String,Object> converPPTXtoImage(String orignalPPTFileName,String targetImageFileDir,  
            String imageFormatNameString){  
        Map<String,Object> map=new HashMap<String, Object>();  
        boolean converReturnResult=true;//是否全部转成功  
        List<String> imgNamesList=new ArrayList<String>();//PPT转成图片后所有名称集合  
          
          
        FileInputStream orignalPPTFileInputStream=null;  
        FileOutputStream orignalPPTFileOutStream=null;  
        XMLSlideShow oneSlideShow=null;  
          
        try{  
            try {  
                orignalPPTFileInputStream=new FileInputStream(orignalPPTFileName);  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                converReturnResult=false;  
                map.put("converReturnResult", converReturnResult);  
                return map;  
            }  
              
            try {  
                oneSlideShow=new XMLSlideShow(orignalPPTFileInputStream);  
            } catch (IOException e) {  
                e.printStackTrace();  
                converReturnResult=false;  
                map.put("converReturnResult", converReturnResult);  
                return map;  
            }  
            //获取PPT每页的尺寸大小（宽和高度）  
            Dimension onePPTPageSize=oneSlideShow.getPageSize();  
            //获取PPT文件中的所有PPT页面，并转换为一张张播放片  
            List<XSLFSlide> pptPageXSLFSLiseList= oneSlideShow.getSlides();  
              
            String xmlFontFormat="<xml-fragment xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:p=\"http://schemas.openxmlformats.org/presentationml/2006/main\">"+   
                                    "<a:rPr lang=\"zh-CN\" altLang=\"en-US\" dirty=\"0\" smtClean=\"0\"> "+  
                                    "<a:latin typeface=\"+mj-ea\"/> "+    
                              "</a:rPr>"+    
                            "</xml-fragment>";    
              
              
            for (int i = 0; i < pptPageXSLFSLiseList.size(); i++) {  
                /** 
                 * 设置中文为宋体，解决中文乱码问题 
                 */  
                CTSlide oneCTSlide=pptPageXSLFSLiseList.get(i).getXmlObject();  
                CTGroupShape oneCTGroupShape=oneCTSlide.getCSld().getSpTree();  
                 List<CTShape>  oneCTShapeList=oneCTGroupShape.getSpList();  
                 for (CTShape ctShape : oneCTShapeList) {  
                     CTTextBody oneCTTextBody = ctShape.getTxBody();  
                       
                     if(null==oneCTTextBody){  
                         continue;  
                     }  
                     CTTextParagraph[]  oneCTTextParagraph= oneCTTextBody.getPArray();  
                     CTTextFont oneCTTextFont=null;  
                     try {  
                        oneCTTextFont=CTTextFont.Factory.parse(xmlFontFormat);  
                    } catch (XmlException e) {  
                        e.printStackTrace();  
                    }  
                       
                     for (CTTextParagraph ctTextParagraph : oneCTTextParagraph) {  
                         CTRegularTextRun[]  onrCTRegularTextRunArray=ctTextParagraph.getRArray();  
                         for (CTRegularTextRun ctRegularTextRun : onrCTRegularTextRunArray) {  
                             CTTextCharacterProperties  oneCTTextCharacterProperties =ctRegularTextRun.getRPr();  
                             oneCTTextCharacterProperties.setLatin(oneCTTextFont);  
                               
                        }  
                           
                    }  
                }  
                   
                 //创建BufferedImage 对象，图像尺寸为原来的PPT的每页尺寸  
                   
                 BufferedImage oneBufferedImage=new BufferedImage(onePPTPageSize.width, onePPTPageSize.height, BufferedImage.TYPE_INT_RGB);  
                 Graphics2D  oneGraphics2D = oneBufferedImage.createGraphics();  
                 //将PPT文件中的每个页面中的相关内容画到转换后的图片中  
                 pptPageXSLFSLiseList.get(i).draw(oneGraphics2D);  
                 /** 
                  * 设置图片的存放路径和图片格式，注意生成的文件路径为绝对路径，最终获得各个图像文件所对应的输出流的对象 
                  */  
                   
                 try {  
                     String imgName="pic_"+i+"."+imageFormatNameString;  
                     System.out.println(imgName);
                     imgNamesList.add(imgName);//将图片名称添加的集合中  
                       
                     orignalPPTFileOutStream=new FileOutputStream(targetImageFileDir+imgName);  
//                  orignalPPTFileOutStream=new FileOutputStream("pict_"+i+"."+imageFormatNameString);  
                } catch (FileNotFoundException e) {  
                    e.printStackTrace();  
                    converReturnResult=false;  
                    map.put("converReturnResult", converReturnResult);  
                    return map;  
                }  
                   
                 //将转换后的各个图片文件保存带指定的目录中  
                 try {  
                    ImageIO.write(oneBufferedImage, imageFormatNameString, orignalPPTFileOutStream);  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    converReturnResult=false;  
                    map.put("converReturnResult", converReturnResult);  
                    return map;  
                }  
                   
                  
            }  
        
              
        } finally{  
            try {  
                if(orignalPPTFileInputStream!=null){  
                    orignalPPTFileInputStream.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
              
            try {  
                if(orignalPPTFileOutStream!=null){  
                    orignalPPTFileOutStream.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            map.put("converReturnResult", converReturnResult);  
            map.put("imgNames", imgNamesList);  
        }  
          
          
          
        return map;  
    }  
}