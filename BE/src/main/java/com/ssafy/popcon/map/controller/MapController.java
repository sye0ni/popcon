package com.ssafy.popcon.map.controller;


import com.ssafy.popcon.map.dto.MapDto;
import com.ssafy.popcon.map.service.MapNearbySearchService;
import com.ssafy.popcon.map.service.MapSearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maps")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    private final MapNearbySearchService mapNearbySearchService;
    private final MapSearchService mapSearchService;

    @GetMapping("/nearby")
    public ResponseEntity<?> mapNearbyList(@RequestParam("lat") double latitude, @RequestParam("lng") double longitude) throws Exception{

        List<MapDto> popups=mapNearbySearchService.findNearbyPopups(latitude,longitude);
//        System.out.println(popups.get(0));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popups);
    }

    @GetMapping("/search")
    public ResponseEntity<?> mapSearchList(@RequestParam Map<String,String> parameter) throws Exception {
        List<MapDto> popups=mapSearchService.findList(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popups);
    }

}
