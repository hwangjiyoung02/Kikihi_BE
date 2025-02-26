//package org.eightbit.damdda.common.config;
//// Java
//import io.swagger.v3.oas.annotations.Hidden;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.eightbit.damdda.order.service.dto.request.OrderRequestDto;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@Tag(name = "예제 API", description = "Swagger 테스트용 API")
//@RestController
//@RequiredArgsConstructor // final로 선언된 필드를 파라미터로 받는 생성자를 생성
//@RequestMapping("/api/v2/order")
//public class SwaggerController {
//
//    private final OrderService orderService;
//
//    //1. 주문 생성
//    @PostMapping("/create")
//    @Operation(summary = "주문 생성",
//                    responses = {
//                        @ApiResponse(responseCode = "200", description = "주문이 성공적으로 생성되었습니다."),
//                        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
//                        @ApiResponse(responseCode = "500", description = "서버 오류 발생")
//                    },
//                    parameters = {
//                        @Parameter(name = "orderRequestDto", description = "주문 요청 정보", required = true)
//                    },
//                    tags = {"주문 API"})
//   public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
//        OrderResponseDto orderResponseDto=orderService.createOrder(orderRequestDto);
//        return ApiResponse.created(orderResponseDto);// 서비스의 응답형태는 -> OrderResponseDto
//    }
//
//    //2. 주문 상세 조회(orderId로 조회)
//    @Operation(summary = "주문 상세 조회", description = "OrderReponseDto반환")
//
//
//    //3. 사용자 모든 주문 정보, 결제 정보 가져오기
//
//    //4. order_id로 주문 취소
//
//    //5. 주문 상태 업데이트
//
//    //6.프로젝트 주최자가 모든 주문 정보를 가져옴
//
//    //    //7. member_id로 프로젝트 Id 가져오기
//
//
//}