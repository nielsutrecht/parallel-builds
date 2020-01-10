package com.nibado.example.parallelbuild

import com.nibado.example.parallelbuild.clients.MyIpClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Controller
class MvcController {
	@RequestMapping("/")
	fun index(): String {
		return "redirect:index.html"
	}
}

@RestController
class ApiController {
	val client = MyIpClient()
	@GetMapping("/api/ip")
	fun ip() = IpResponse(client.ip())

	data class IpResponse(val ip: String)
}

@Configuration
class WebConfig : WebMvcConfigurer {
	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		println(File(".").absolutePath)

		registry
			.addResourceHandler("/**")
			.addResourceLocations("file:./front-end/")
	}
}