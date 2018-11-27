//package com.felix.springbootdemo.model;
//
//import com.felix.springbootdemo.service.impl.UserServiceImpl;
//import lombok.Getter;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
//@Getter
//public enum Source {
//    GDT {
//        @Override
//        public User getTest(RabbitTemplate rabbitTemplate, Integer id) {
//            return new UserServiceImpl(rabbitTemplate).getUser(id);
//        }
//    },
//    ZDH {
//        @Override
//        public User getTest(RabbitTemplate rabbitTemplate, Integer id) {
//            return null;
//        }
//    },
//    INIT {
//        @Override
//        public User getTest(RabbitTemplate rabbitTemplate, Integer id) {
//            return null;
//        }
//    },
//    MATERIAL {
//        @Override
//        public User getTest(RabbitTemplate rabbitTemplate, Integer id) {
//            return null;
//        }
//    };
//
//    public abstract User getTest(RabbitTemplate rabbitTemplate, Integer id);
//}
