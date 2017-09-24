package com.check;

import com.check.dao.CourseRepository;
import com.check.dao.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void contextLoads() {

	}

	/*@Test
	public void save(){
		Teacher teacher = new Teacher();
		teacher.setEmail("123@163.com");
		teacher.setPassword("123");
		Course course = new Course();
		course.setCourseID(1);
		course.setCourseName("java");
		course.setPeriod(32);
		teacher.getCourses().add(course);
		courseRepository.save(course);
		teacherRepository.save(teacher);

	}*/
	/*@Test
	public void query(){
		Teacher teacher = teacherRepository.findByEmail("123@163.com");
		System.out.println(teacher);
	}*/

	/*@Test
	public void delete(){
		*//*Teacher teacher = teacherRepository.findByEmail("123@163.com");
		Course course = courseRepository.findByCourseID(1);
		teacher.removeCourse(course);*//*
		//courseRepository.save(course);
		//teacherRepository.save(teacher);
		courseRepository.deleteByCourseID(1);
		//System.out.println(teacher);
		//teacher.addCourse(new Course(2,"c",16,teacher));



	}*/

	/*@Test
	public void testPage(){
		//index 1 从0开始 不是从1开始的
		Sort.Order order =  new Sort.Order(Sort.Direction.DESC,"id");
		Sort sort = new Sort(order);
		Pageable page = new PageRequest(0,5,sort);
		Page<Course> courses = courseRepository.findAll(page);
		System.out.println("查询总页数:"+courses.getTotalPages());
		System.out.println("查询总记录数:"+courses.getTotalElements());
		System.out.println("查询当前第几页:"+courses.getNumber()+1);
		System.out.println("查询当前页面的集合:"+courses.getContent());
		System.out.println("查询当前页面的记录数:"+courses.getNumberOfElements());
	}*/

}
