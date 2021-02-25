package imageboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value="imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@RequestMapping(value="imageboardWriteForm",method=RequestMethod.GET)
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display","/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}
	
	/*
	//name="img" 1개인 경우.
	@RequestMapping(value="imageboardWrite",method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam MultipartFile img) {
		
		//가장 중요한 것은 imageboardWriteForm 에서 image1 의 name을 DTO로넘어오지못하도록 변수명을 바꿔줘야 한다는 것이다.그 이유는DTO가 아닌, file로 업로드를 하기 위함.
		
		
		//가상경로로 파일을 복사.
		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
		String fileName = img.getOriginalFilename(); //자신의 본래 파일 이름으로 바뀐다.
		File file = new File(filePath, fileName);
		가상경로에 접근하여 파일을 생성해 주게 되면 실제폴더에 파일이 생성된다.
		
		//파일 복사
		try {
			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));//copy(in, out) in으로 들어온걸 out 으로 복사해라.
			//img의 파일 경로를 읽어서  file 에게 복사해줘라.
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageboardDTO.setImage1(fileName);
		
	}
	*/
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	//name="img" 2개이상인 경우. - 같은 name변수를 배열로 받는다.
	/*
	@RequestMapping(value="imageboardWrite",method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam MultipartFile[] img) {
		

		
		
		//가상경로로 파일을 복사.
		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
		String fileName; //자신의 본래 파일 이름으로 바뀐다.
		File file;
		
		//파일 복사
		
		//만약 여러개의 이미지를 사용하고 싶은 경우 나중에 for문을 돌리면된다.
		if(img[0] != null) {
			fileName = img[0].getOriginalFilename();
			file = new File(filePath, fileName);
			try {
				FileCopyUtils.copy(img[0].getInputStream(), new FileOutputStream(file));//copy(in, out) in으로 들어온걸 out 으로 복사해라.
				//img의 파일 경로를 읽어서  file 에게 복사해줘라.
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName);
		}else {
			imageboardDTO.setImage1("");//img가 없다 빈값을 저장해라.
		}
		//-------
		if(img[1] != null) {
         	fileName = img[1].getOriginalFilename();
         	file = new File(filePath, fileName);
         
         try {
            	FileCopyUtils.copy(img[1].getInputStream(), new FileOutputStream(file));
         } catch (IOException e) {
            	e.printStackTrace();
         }
         
			imageboardDTO.setImage2(fileName);
		}else {
			imageboardDTO.setImage2("");
		}
		
		
	}//imageboardWrite
	*/
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	//드래그해서 한번에 여러개의 파일을 선택
	@RequestMapping(value="imageboardWrite",method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam("img[]") List<MultipartFile> list) { //name 이 img[] 인 변수의 값을 list로 받겠다. 
		//RequestParam 으로 list에 데이터를 받아올 때, 이미 @RequestParam을 통해서 Form의 img 데이터에 담겨있는 선택된 파일이 실제경로로 올라간다.
		
		//실제경로에 저장되어 있는 이미지 파일은 임시파일(*.tmp)로 저장이 되어져 있기 때문에, 임시파일을 사용하는 것이 아닌,
		//실제 파일과 같은 이름, 같은 내용을 복사해서 가상폴더에서 사용하는 것이다.
		
		//그 후, 가상경로(storage)에 fileName에 대한 파일을 생성한다. 단, 이 파일은 파일명만 가지고있다.(내용이없음)
		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
						   
		for(MultipartFile img : list) {
			System.out.println("img = " + img);
			String fileName = img.getOriginalFilename(); //자신의 본래 파일 이름으로 바뀐다.
			File file = new File(filePath, fileName);
			
			//파일(내용) 복사
			//list안에는 form 데이터에서 선택된 복수개의 파일들이 들어있고, 그걸 img 로 하나씩 빼낸다.
			//그래서 img.getInputStream을 하게 되면, 실제 경로에 저장된 list 에 있는 파일들의
			//내용을 out쪽에 넘겨주어 file 변수에 실제 파일의 내용을 복사하는 것이다.
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));//copy(in, out) in으로 들어온걸 out 으로 복사해라.
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");
			//image1에만 이미지가 몰아서 넣어지기 때문에 image2는 빈값으로 둔다.
			
			//DB
			imageboardService.imageboardWrite(imageboardDTO);
		}//for
	}
	
	@RequestMapping(value="imageboardList",method=RequestMethod.GET)
	public String imageboardList(@RequestParam(required=false,defaultValue = "1") String pg,
								Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display","/imageboard/imageboardList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getImageboardList",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam String pg) {
		//1페이지당 5개
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);
		
		//페이징 처리
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("imageboardPaging",imageboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="imageboardView",method=RequestMethod.GET)
	public String imageboardView(@RequestParam String seq,
								 @RequestParam String pg,
								 Model model) {
		model.addAttribute("seq",seq);
		model.addAttribute("pg",pg);
		model.addAttribute("display","/imageboard/imageboardView.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getImageboardView",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("imageboardDTO", imageboardDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	//imageboardDelete.jsp로가서 alert창을 띄우고 페이지를 List로이동하기.
	
//	@RequestMapping(value="imageboardDelete",method=RequestMethod.GET)
//	public String imageboardDelete(@RequestParam String[] check, Model model) {
//		//checbox 의 name 변수들을 모두 check 로 해 놓았으므로 String[] 로 값이 들어온다.
//		imageboardService.imageboardDelete(check);
//		
//		model.addAttribute("display","/imageboard/imageboardDelete.jsp");
//		return "/index";
//	}
	
	
	//jsp로 가지 않고 DispatcherServlet으로 가서 바로 다시 Controller로 들어옴. 그러므로 jsp를 거치지않음
	@RequestMapping(value="imageboardDelete",method=RequestMethod.GET)
	public ModelAndView imageboardDelete(@RequestParam String[] check) {
		//checbox 의 name 변수들을 모두 check 로 해 놓았으므로 String[] 로 값이 들어온다.
		imageboardService.imageboardDelete(check);
		
		return new ModelAndView("redirect:/imageboard/imageboardList");
	}//페이지에 대한 내용을 모두 지웠으므로, 다시 요청지로 돌아가는 것이 아닌 List로 돌아가서 페이징 처리를 다시해준다 
	
}
