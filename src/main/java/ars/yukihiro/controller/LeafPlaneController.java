package ars.yukihiro.controller;

import ars.yukihiro.enums.ContentsType;
import ars.yukihiro.enums.NodeType;
import ars.yukihiro.exception.ResourceNotFoundException;
import ars.yukihiro.message.ApplicationMessageBundle;
import ars.yukihiro.enums.ApplicationMessageId;
import ars.yukihiro.response.form.LeafPlaneForm;
import ars.yukihiro.service.INodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * leafNode/planeコントローラ.
 * @auther yukihiro adachi
 */
@Controller
@RequestMapping("leaf/plane")
public class LeafPlaneController {

    private static final Logger logger =
            LoggerFactory.getLogger(LeafPlaneController.class);

    @Autowired
    private INodeService leafPlaneService;

    @RequestMapping(path = {"/{nodeId}"}, method = RequestMethod.GET)
    public ModelAndView doGet(
            @PathVariable Optional<Integer> nodeId,
            ModelAndView mv) {
        try {
            LeafPlaneForm form;
            if (nodeId.isEmpty()) {
                // 新規
                form = new LeafPlaneForm();
                form.setNodeType(NodeType.INTERNAL);
                form.setContentsType(ContentsType.PLANE);
            } else {
                // 編集
                form = (LeafPlaneForm) leafPlaneService.getNodeForm(nodeId.get());
                if (form == null) {
                    throw new ResourceNotFoundException(
                            String.format("contentsId:%s",  nodeId.get()));
                }
            }
            mv.addObject("planeForm", form);
            mv.setViewName("leaf_plane");
            return mv;
        } catch (Exception e) {
            logger.error(
                    ApplicationMessageBundle.getMessage(
                            ApplicationMessageId.SYS_E_01), e);
            throw e;
        }
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT} )
    public ResponseEntity<Map<String, Object>> doPut(@Validated LeafPlaneForm form,
                                                      BindingResult result) {
        // TODO 他のコントローラとの共通化について検討
        Map<String, Object> responseBody = new HashMap<>();

        if (result.hasErrors()) {
            for(FieldError error : result.getFieldErrors()) {
                responseBody.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            try {
                leafPlaneService.upsertNodeByForm(form);
                responseBody.put("message",
                        ApplicationMessageBundle.getMessage(
                                ApplicationMessageId.SYS_I_01, "登録／更新"));
                return ResponseEntity.ok(responseBody);
            } catch (Exception e) {
                responseBody.put("message",
                        ApplicationMessageBundle.getMessage(ApplicationMessageId.SYS_E_01));
                logger.error(
                        ApplicationMessageBundle.getMessage(
                                ApplicationMessageId.SYS_E_01), e);
                return ResponseEntity.badRequest().body(responseBody);
            }
        }
    }
}
