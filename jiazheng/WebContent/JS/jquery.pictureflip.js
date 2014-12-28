(function($, w, d, undefined){
	
		
	// Modernizr for IE8. Fallback is pretty basic, but lets not worry too much
	// about IE8.
	var Modernizr = w.Modernizr;
	
	$.pageFlip = function(options, element) {
		
		// This.
		$this = this;
		
		// Element
		this.element = element;
		
		// For jQuery
		this.$element = $(element);

		// Run function
		this.init(options);
		
		
	}
	
	$.pageFlip.defaults = { 
		
		// Time duration of the page flip in seconds.
		time : 1,
		
		// Turn shadow on? This can cause some performance issues on some devices.
		shadow: true,
		
		// The time it takes for the content of each slide to slide up in seconds.
		slideTime: 0.2,
		
		// Autorun? If this is true the slides will flip over automatically at a constant speed.
		autoRun: false,
		
		// The time between automatic page flips in seconds.
		autoTime: 5
		
	}
	
	$.pageFlip.prototype = {  
		
		init: function(options) {
			
			// Extend defaults
			this.options = $.extend(true, {}, $.pageFlip.defaults, options);
			
			// Is it being animated?
			this.animated = false;
			
			// Just regular slider if no 3D transforms
			if(!Modernizr.csstransforms3d) {
				this.options.time = 0;
				this.options.slideTime = 0;
			}

			// Change the images so they can fold
			this.append();	
			
			// Pagination
			this.pagination(true);
			
			// Autorun
			if(this.options.autoRun === true) {
				this.auto();
			}
		},
		
		append : function() {
			
			// Append the UI
			this.$element.append('<div class="prev">&#x25C5;</div><div class="next">&#x25BB;</div>');
			// Set the transition using jQuery set time
			this.$element.find('.content').css({'transition' : 'all '+$this.options.slideTime+'s linear'});
			
			// Set slide height
			$('.slide').height($this.$element.height() - 40)
			
			// Add the class current to the first slide
			$('.slide:first').addClass('current');	
			// Then slide the content for the first slide up
			$('.slide:first .content').addClass('slideup');
			
			// if not IE8 basically
			if(Modernizr.csstransforms3d) {
			
				// For each slide
				this.$element.find('.slide').each(function() {
					
					// Append two image containers, one for each side of the slide
					$(this).append('<div class="img"></div><div class="img"></div>');
				
					// Duplicate images
					var image = $(this).find('img').css('display', 'none').attr('src');
					
					// Apply the background and transition duration to each bit. Transition duration is half due to two transitions occuring
					// One to get to the middle and one to flip over
					$(this).find('.img').css({
						'background' : 'url("'+image+'")', 
						'transition-duration' : ($this.options.time/2)+'s, '+($this.options.time/2)+'s'
					});
					
					// The last image should be on the right side, so adjust that
					$(this).find('.img:last-of-type').css({'right' : '0', 'left' : 'auto', 'background-position' : '100% 0'});
					
				});
			
			}
			
		},
		
		events : function() {
			
			// For the next button click
			function nextClick(quick) {
				
				// Get the value of ntime, the length of the animation in ms.
				if(quick === false)  var ntime = $this.options.time * 1000, stime = $this.options.slideTime * 1000;
				else var ntime = $this.options.time, stime = $this.options.slideTime;
				
				// If the animation isn't running
				if($this.animated === false) {
					
					// Slide up the content
					$('.slide:first .content').removeClass('slideup');	
					
					// Animation is now running
					$this.animated = true;
					
					// If this is clicking the circle buttons at the bottom, ignore all of it
					if(quick === false) {
						
						// Otherwise find the next and current slides
						var current = $('.current .img:last-of-type', $this.$element);
						var next = $('.current', $this.$element).nextAll('.slide:first').find('.img:first');
					
						// Change transition time for slide images
						$('.slide .img').css({'transition-duration' : ($this.options.time/2)+'s'});
					
						// The next slide will rotate and have opacity set to 0. This is because until the flip reaches the middle
						// we can't actually see this slide
						next.css({
							'opacity' : '0', 
							'transform' : 'rotateY(90deg)', 
							'transform-origin' : '100% 100%',
							
						});
						
						// If shadow is activated, add a shadow
						if($this.options.shadow === true) {
							next.css({'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'});
							current.css({'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'});	
						}
						
						// After the slide time, adjust z-index and transform as required
						setTimeout(function() { 
							$('.current').nextAll('.slide:first').css('z-index', '999');
							current.css({
								'transform' : 'rotateY(-90deg)',
							});
						}, stime);
						
						// Half way through, show the next slide and rotate it round fully.
						setTimeout(function() {
							
							next.css({
								'opacity' : '1',
								'transform' : 'rotateY(0deg)',
								'box-shadow' : 'none'
							});
							
							$('.current').nextAll('.slide:first').css({'z-index' : '999999'});
							
						}, ntime/2 + stime);
									
					}
					
					// Finally set everything back to normal at the end as if nothing happened
					setTimeout(function() {
						
						$('.current').nextAll('.slide:first').addClass('current').css({
							'z-index' : ''
						}).siblings().removeClass('current');
						
						$('.slide > .img:first').css({
							'transform' : 'none', 
							'left' : '0', 'right' : '0', 
							'box-shadow' : 'none'
						});
						$('.slide > .img:last').css({
							'transform' : 'none', 
							'transition-duration' : '0', 
							'box-shadow' : 'none'
						});
						$('.slide:first', $this.$element).appendTo($this.$element);
						
						$this.animated = false;
						
						$this.pagination(false);
						
						if(quick === false) {
							$('.slide:first .content').addClass('slideup');
						}
						
					}, ntime + stime);
										
				}
				
			}
			
			// The same as for next click only the other way around
			function prevClick(quick) {
				
				// Get the value of ntime, the length of the animation in ms.
				if(quick === false) var ntime = $this.options.time * 1000, stime = $this.options.slideTime * 1000;
				else var ntime = $this.options.time, stime = $this.options.slideTime;
				
				// Assuming the animation isn't running
				if($this.animated === false) {
				
					$this.animated = true;
					
					$('.slide:first .content').removeClass('slideup');	
						
					if(quick === false) {
					
						var current = $('.current .img:first', $this.$element);
						var prev = $('.current', $this.$element).nextAll('.slide:last').find('.img:last');
						
						$('.slide .img').css({'transition-duration' : ($this.options.time/2)+'s'});
					
						setTimeout(function() {
							prev.css({
								'opacity' : '0', 
								'transform' : 'rotateY(90deg)', 
								'transform-origin' : '0 0',
								'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'
							});
							
							current.css({
								'transform' : 'rotateY(90deg)',
								'transform-origin' : '100% 100%',
								'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'
							});
							
							if($this.options.shadow === true) {
								prev.css({'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'});
								current.css({'box-shadow' : 'inset 0 0 2000px 5000px rgba(0,0,0,0.3)'});	
							}
						}, stime);
						
						setTimeout(function() {
							
							prev.css({
								'opacity' : '1',
								'transform' : 'rotateY(0)',
								'box-shadow' : 'none'
							});
							
							$('.slide:last').css({'z-index' : '999999'});
						
						}, ntime/2 + stime)		
									
					}
					setTimeout(function() {
						
						$('.slide:last').addClass('current').css({'z-index' : ''}).siblings().removeClass('current');
						$('.slide > .img:first').css({
							'transform' : 'none', 
							'left' : '0', 
							'right' : '0', 
							'transition-duration' : '0',
							'box-shadow' : 'none'
						});
						$('.slide > .img:last').css({
							'transform' : 'none',
							'box-shadow' : 'none'
						});
						$('.slide:last', $this.$element).prependTo($this.$element);
						
						$this.animated = false;
						
						$this.pagination(false);
						
					}, ntime + stime);
					
					// Due to a little problem where the content wouldn't slide in.
					setTimeout(function() {
						if(quick === false) {
							$('.slide:first .content').addClass('slideup');
						}
					}, ntime + stime + 1);
				}

			}
			
			// The next slide button
			$('.next', $this.$element).on("click", function() {
				nextClick(false)
			});
			
			// The previous slide button
			$('.prev', $this.$element).on("click", function() {
				prevClick(false)
			});
		
			// The pagination buttons click
			$('#pagination div', $this.$element).on("click", function() {
				
				// Some variables to get the slide ID and the current pagination button ID
				var slideid = $(this).attr('class').split(' ')[0].split('-')[1],
					curid = $('#pagination .cur').attr('class').split(' ')[0].split('-')[1],
					diff = slideid - curid, // The difference between the slide ID and current pagination button ID
					clicker = nextClick; 
				
				// Clicker will by default call the next slide, but if the user clicks a button before the current slide
				// Then clicker will become the previous slide
				if(diff < 0) {
					clicker = prevClick;
				}
				
				
				// Variables for interval
				var i = 0,
					prun;
			
				// Set up an interval
				var prun = setInterval(function() {
					
					// Set diff to a positive number
					diff = Math.abs(diff);
					
					// Run the clicker function until the difference is equalized
					if(diff != 0 && i < diff) {
						clicker(true);
					}
					
					// If the difference is equal to i then clear the interval and show the content
					if(i === diff || diff === 0) {
						clearInterval(prun);
						$('.slide:first .content').addClass('slideup');
					}
					
					// Increasing the i variable
					++i;
					
				}, (($this.options.time*10)) + ($this.options.slideTime*10));
					
				
			});
		},
		
		pagination: function(append) {
			
			// Some variables.
			var i = 0,
				classid;
			
			// For appending the pagination data.
			if(append === true) {
				
				// Append a container
				$(this.element).append('<div id="pagination"></div>');
				
				// For each slide
				this.$element.find('.slide').each(function() {
					
					// Add a class to the slide defining its ID
					$(this).addClass('s-'+i);
					
					// Then append a button with that same ID to the pagination div
					$('#pagination').append('<div class="p-'+i+'"></div>');
					
					// Increase i.
					++i;
						
				});
				
				// The current slide ID is therefore the current slide's class attribute split to get the number
				classid = $('.current').attr('class').split(' ')[2].split('-')[1];
				
				// Running events here so we can click the pagination
				this.events();
				
			} else {
				
				// Otherwise the classid is different because of the way jQuery appends classes
				classid = $('.current').attr('class').split(' ')[1].split('-')[1];
			
			}
			
			// Run through each pagination div
			$('#pagination div').each(function() {
				
				// The ID for that div is as follows
				var pageid = $(this).attr('class').split('-')[1];

				// If the pagination div's ID equals the class ID (the slide ID)
				if(pageid === classid) {
					// Then add the class cur to the current pagination div
					$(this).addClass('cur');
				
				} else {
					// Otherwise remove it
					$(this).removeClass('cur');
			
				}
				
			})
				
		},
		
		auto: function() {
			
			// For auto sliding (if enabled)
			var autoslide,
				hovering = false;
			
			// When the mouse is over hovering is true, and clear interval
			$this.$element.on("mouseover", function() {
			
				hovering = true;
				
				clearInterval(autoslide);	
				
			});
			
			// Otherwise hovering is false
			$this.$element.on("mouseout", function() {
				
				hovering = false;
				
			});
			
			// On hovering over the window, clear the interval
			$(w).on("hover", function() {
				
				clearInterval(autoslide);	
				
				// But if hovering is false, set the interval
				if(hovering === false) {
					
					autoslide = setInterval(function() {
						
						$this.$element.find('.next').click();
						
					}, ($this.options.autoTime * 1000));
					
				}
			});
			
		}
		
	}

	$.fn.pageFlip = function(options, content) {
		
		return this.each(function() {
			
			new $.pageFlip(options, this);
			
		});
	
	};
	
})(jQuery, window, document);