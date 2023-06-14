/** 
 * PrimeFaces Apollo Layout
 */
PrimeFaces.widget.Apollo = PrimeFaces.widget.BaseWidget.extend({
    
    init: function(cfg) {
        this._super(cfg);
        this.wrapper = $(document.body).children('.layout-wrapper');
        this.topbar = this.wrapper.children('.topbar');
        this.menuContainer = this.wrapper.children('.layout-menu-container');
        this.menu = this.jq;
        this.menuWrapper = this.menu.closest('.layout-menu');
        this.menulinks = this.menu.find('a');
        this.expandedMenuitems = this.expandedMenuitems||[];
        this.profileButton = this.topbar.children('.profile');
        this.topbarMenu =  this.topbar.children('.topbar-menu');
        this.topbarLinks = this.topbarMenu.find('a');
        this.menuButton = $('#menu-button');
        this.menuActive = false;
        this.topbarMenuClick = false;
        this.topbarMenuButtonClick = false;

        this.configButton = $('#layout-config-button');
        this.configMenu = $('#layout-config');
        this.configMenuClose = this.configMenu.find('> .layout-config-content > .layout-config-close');

        this._bindEvents();
        
        if(!this.isHorizontal() && !this.isSlim()) {
            this.restoreMenuState();
        }
    },

    _bindEvents: function() {
        var $this = this;
        
        this.menu.off('click.menu').on('click.menu', function(e) {  
            $this.menuClick = true;
        });
        
        this.menuButton.off('click.menu').on('click.menu', function(e) {  
            $this.menuButtonClick = true;
            
            if($this.isDesktop()) {
                if($this.isOverlay()) 
                    $this.wrapper.toggleClass('layout-overlay-active');
                else if($this.isStatic()) 
                    $this.wrapper.toggleClass('layout-static-inactive');
            }
            else {
                $this.wrapper.toggleClass('layout-mobile-active');
            }
                          
            e.preventDefault();
        });
        
        this.profileButton.off('click.profile').on('click.profile', function(e) {
            $this.topbarMenuButtonClick = true;
            $this.topbarMenu.toggleClass('topbar-menu-visible');
                        
            e.preventDefault();
        });
        
        this.menulinks.off('click.menu').on('click.menu', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = item.children('ul'),
            horizontal = $this.isHorizontal(),
            slim = $this.isSlim();
                                                 
            if(item.hasClass('active-menuitem')) {
                if(submenu.length) {
                    $this.removeMenuitem(item.attr('id'));
                    item.removeClass('active-menuitem');
                    
                    if(horizontal || slim) {
                        if(item.parent().is($this.jq)) {
                            $this.menuActive = false;
                        }
                        
                        submenu.hide();
                    }
                    else {
                        submenu.slideUp();
                    }
                }
            }
            else {
                $this.addMenuitem(item.attr('id'));
                
                if(horizontal || slim) {
                    $this.deactivateItems(item.siblings());
                    item.addClass('active-menuitem');
                    $this.menuActive = true;
                    submenu.show();
                }
                else {
                    $this.deactivateItems(item.siblings(), true);
                    $this.activate(item);
                }
            }

            if(submenu.length) {
                e.preventDefault();
            }
        });
        
        this.topbarLinks.off('click.topbar').on('click.topbar', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = link.next();
            
            item.siblings('.menuitem-active').removeClass('menuitem-active');
            
            if(item.hasClass('menuitem-active')) {
                item.removeClass('menuitem-active');
                link.next('ul').slideUp();
            }
            else {
                item.addClass('menuitem-active');
                link.next('ul').slideDown();
            }
            
            if(submenu.length) {
                e.preventDefault();   
            }
        });
        
        this.topbarMenu.off('click.topbar').on('click.topbar', function() {
            $this.topbarMenuClick  = true;
        });
        
        this.menu.find('> li').off('mouseenter.menuitem').on('mouseenter.menuitem', function(e) {    
            if(($this.isHorizontal() || $this.isSlim()) && $this.isDesktop()) {
                var item = $(this);
                
                if(!item.hasClass('active-menuitem')) {
                    $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                    $this.menu.find('ul:visible').hide();
                    $this.menu.find('.ink').remove();
                    
                    if($this.menuActive) {
                        item.addClass('active-menuitem');
                        item.children('ul').show();
                    }
                }
            }
        });

        this.bindConfigEvents();
                                
        $(document.body).off('click.layoutBody').on('click.layoutBody', function() {
            if(($this.isHorizontal() || $this.isSlim()) && !$this.menuClick && $this.isDesktop()) {
                $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                $this.menu.find('ul:visible').hide();
                $this.menuActive = false;
            }
            
            if(!$this.topbarMenuClick && !$this.topbarMenuButtonClick) {
                $this.topbarMenu.removeClass('topbar-menu-visible');
            }
            
            if(!$this.menuButtonClick && !$this.menuClick) {
                $this.wrapper.removeClass('layout-overlay-active layout-mobile-active');
            }

            if (!$this.configMenuClicked) {
                $this.configMenu.removeClass('layout-config-active');
            }
            
            $this.menuButtonClick = false;
            $this.menuClick = false;
            $this.topbarMenuClick = false;
            $this.topbarMenuButtonClick = false;
            $this.configMenuClicked = false;
        });
    },
         
    bindConfigEvents: function() {
        var $this = this;
        var changeConfigMenuState = function(e) {
            this.toggleClass(this.configMenu, 'layout-config-active');
            
            this.configMenuClicked = true;
            e.preventDefault();
        };
        
        this.configButton.off('click.config').on('click.config', changeConfigMenuState.bind(this));
        this.configMenuClose.off('click.config').on('click.config', changeConfigMenuState.bind(this));
        
        this.configMenu.off('click.config').on('click.config', function() {
            $this.configMenuClicked = true;
        });
    },

    activate: function(item) {
        var submenu = item.children('ul');
        item.addClass('active-menuitem');

        if(submenu.length) {
            submenu.slideDown();
        }
    },

    deactivate: function(item) {
        var submenu = item.children('ul');
        item.removeClass('active-menuitem');
        
        if(submenu.length) {
            submenu.hide();
        }
    },
        
    deactivateItems: function(items, animate) {
        var $this = this;
        
        for(var i = 0; i < items.length; i++) {
            var item = items.eq(i),
            submenu = item.children('ul');
            
            if(submenu.length) {
                if(item.hasClass('active-menuitem')) {
                    var activeSubItems = item.find('.active-menuitem');
                    item.removeClass('active-menuitem');
                    item.find('.ink').remove();
                    
                    if(animate) {
                        submenu.slideUp('normal', function() {
                            $(this).parent().find('.active-menuitem').each(function() {
                                $this.deactivate($(this));
                            });
                        });
                    }
                    else {
                        submenu.hide();
                        item.find('.active-menuitem').each(function() {
                            $this.deactivate($(this));
                        });
                    }
                    
                    $this.removeMenuitem(item.attr('id'));
                    activeSubItems.each(function() {
                        $this.removeMenuitem($(this).attr('id'));
                    });
                }
                else {
                    item.find('.active-menuitem').each(function() {
                        var subItem = $(this);
                        $this.deactivate(subItem);
                        $this.removeMenuitem(subItem.attr('id'));
                    });
                }
            }
            else if(item.hasClass('active-menuitem')) {
                $this.deactivate(item);
                $this.removeMenuitem(item.attr('id'));
            }
        }
    },
            
    removeMenuitem: function (id) {
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
            return value !== id;
        });
        this.saveMenuState();
    },

    addMenuitem: function (id) {
        if ($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
        this.saveMenuState();
    },

    saveMenuState: function() {
        $.cookie('apollo_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
    },

    clearMenuState: function() {
        this.expandedMenuitems = [];
        $.removeCookie('apollo_expandeditems', {path: '/'});
    },

    restoreMenuState: function() {
        var menucookie = $.cookie('apollo_expandeditems');
        if (menucookie) {
            this.expandedMenuitems = menucookie.split(',');
            for (var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if (id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                    menuitem.addClass('active-menuitem');
                    
                    var submenu = menuitem.children('ul');
                    if(submenu.length) {
                        submenu.show();
                    }
                }
            }
        }
    },

    clearActiveItems: function() {
        var activeItems = this.jq.find('li.active-menuitem'),
        subContainers = activeItems.children('ul');

        activeItems.removeClass('active-menuitem');
        if(subContainers && subContainers.length) {
            subContainers.hide();
        }
    },

    clearLayoutState: function() {
        this.clearMenuState();
        this.clearActiveItems();
    },

    isHorizontal: function() {
        return this.wrapper.hasClass('layout-horizontal') && this.isDesktop();
    },
    
    isSlim: function() {
        return this.wrapper.hasClass('layout-slim') && this.isDesktop();
    },
    
    isOverlay: function() {
        return this.wrapper.hasClass('layout-overlay') && this.isDesktop();
    },
    
    isStatic: function() {
        return this.wrapper.hasClass('layout-static') && this.isDesktop();
    },

    isDesktop: function() {
        return window.innerWidth > 991;
    },

    isMobile: function() {
        return window.innerWidth <= 991;
    },
    
    toggleClass: function(el, className) {
        if (el.hasClass(className)) {
            el.removeClass(className);
        }
        else {
            el.addClass(className);
        }
    },
});

PrimeFaces.ApolloConfigurator = {

    changeLayout: function( color, darkMode ) {
        this.changeLayoutsTheme(color, darkMode);
        this.changeDemo(darkMode);
        this.changeComponentsTheme(color, darkMode);
    },

    changeComponentsTheme: function(theme, darkMode) {
        theme = this.getColor(theme, darkMode);
        var library = 'primefaces-apollo';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library) + 1;
        var currentTheme = href.substring(index + library.length);
        
        this.replaceLink(linkElement, href.replace(currentTheme, theme));
    },

    changeLayoutsTheme: function(theme, darkMode) {
        theme = this.getColor(theme, darkMode);
        var linkElement = $('link[href*="layout-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('layout-') + 7;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);
        
        this.replaceLink(linkElement, href.replace(currentColor, theme));
    },

    changeDemo: function(darkMode) {
        newLayout = '-' + darkMode;
        var linkElement = $('link[href*="demo-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('demo-') + 4;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);
    
        this.replaceLink(linkElement, href.replace(currentColor, newLayout));
    },

    changeMenuMode: function(menuMode) {
        var wrapper = $(document.body).children('.layout-wrapper');
        switch (menuMode) {
            case 'layout-static layout-static-active':
                wrapper.addClass('layout-static layout-static-active').removeClass('layout-overlay layout-slim layout-horizontal');
                this.clearLayoutState();
            break;

            case 'layout-overlay':
                wrapper.addClass('layout-overlay').removeClass('layout-static layout-slim layout-horizontal layout-static-active');
                this.clearLayoutState();
            break;

            case 'layout-horizontal':
                wrapper.addClass('layout-horizontal').removeClass('layout-static layout-overlay  layout-slim layout-static-active');
                this.clearLayoutState();
            break;

            case 'layout-slim':
                wrapper.addClass('layout-slim').removeClass('layout-static layout-overlay layout-horizontal layout-static-active');
                this.clearLayoutState();
            break;

            default:
                wrapper.addClass('layout-static').removeClass('layout-overlay layout-slim layout-horizontal ');
                this.clearLayoutState();
            break;
        }
    },

    beforeResourceChange: function() {
        PrimeFaces.ajax.RESOURCE = null;    //prevent resource append
    },
      
    getColor: function(name, darkMode) {
        return name + '-' + darkMode;
    },

    replaceLink: function(linkElement, href) {
        PrimeFaces.ajax.RESOURCE = 'javax.faces.Resource';

        var isIE = this.isIE();

        if (isIE) {
            linkElement.attr('href', href);
        }
        else {
            var cloneLinkElement = linkElement.clone(false);

            cloneLinkElement.attr('href', href);
            linkElement.after(cloneLinkElement);
            
            cloneLinkElement.off('load').on('load', function() {
                linkElement.remove();
            });
        }
    },

    changeMenuToStatic: function() {
        $('.layout-wrapper').removeClass('layout-overlay layout-horizontal layout-slim').addClass('layout-static');
        this.clearLayoutState();
    },

    changeMenuToOverlay: function() {
        $('.layout-wrapper').removeClass('layout-horizontal layout-static layout-slim').addClass('layout-overlay');
        this.clearLayoutState();
    },

    changeMenuToHorizontal: function() {
        $('.layout-wrapper').removeClass('layout-overlay layout-static layout-slim').addClass('layout-horizontal');
        this.clearLayoutState();
    },

    changeMenuToSlim: function() {
        $('.layout-wrapper').removeClass('layout-overlay layout-static layout-horizontal').addClass('layout-slim');
        this.clearLayoutState();
    },

    clearLayoutState: function() {
        var menu = PF('ApolloMenuWidget');

        if (menu) {
            menu.clearLayoutState();
        }
    },
    
    isIE: function() {
        return /(MSIE|Trident\/|Edge\/)/i.test(navigator.userAgent);
    },

    updateInputStyle: function(value) {
        if (value === 'filled')
            $(document.body).addClass('ui-input-filled');
        else
            $(document.body).removeClass('ui-input-filled');
    }
};
/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));

/* JS extensions to support material animations */
if (PrimeFaces.widget.InputSwitch) {
    PrimeFaces.widget.InputSwitch = PrimeFaces.widget.InputSwitch.extend({

        init: function (cfg) {
            this._super(cfg);

            if (this.input.prop('checked')) {
                this.jq.addClass('ui-inputswitch-checked');
            }
        },

        toggle: function () {
            if (this.input.prop('checked')) {
                this.uncheck();
                this.jq.removeClass('ui-inputswitch-checked');
            } else {
                this.check();
                this.jq.addClass('ui-inputswitch-checked');
            }
        }
    });
}

/* Issue #924 is fixed for 5.3+ and 6.0. (compatibility with 5.3) */
if(window['PrimeFaces'] && window['PrimeFaces'].widget.Dialog) {
    PrimeFaces.widget.Dialog = PrimeFaces.widget.Dialog.extend({

        enableModality: function() {
            this._super();
            $(document.body).children(this.jqId + '_modal').addClass('ui-dialog-mask');
        },

        syncWindowResize: function() {}
    });
}

if (PrimeFaces.widget.SelectOneMenu) {
    PrimeFaces.widget.SelectOneMenu = PrimeFaces.widget.SelectOneMenu.extend({
        init: function (cfg) {
            this._super(cfg);

            var $this = this;
            if (this.jq.parent().hasClass('ui-float-label')) {
                this.m_panel = $(this.jqId + '_panel');
                this.m_focusInput = $(this.jqId + '_focus');

                this.m_panel.addClass('ui-input-overlay-panel');
                this.jq.addClass('ui-inputwrapper');

                if (this.input.val() != '') {
                    this.jq.addClass('ui-inputwrapper-filled');
                }

                this.input.off('change').on('change', function () {
                    $this.inputValueControl($(this));
                });

                this.m_focusInput.on('focus.ui-selectonemenu', function () {
                    $this.jq.addClass('ui-inputwrapper-focus');
                })
                    .on('blur.ui-selectonemenu', function () {
                        $this.jq.removeClass('ui-inputwrapper-focus');
                    });

                if (this.cfg.editable) {
                    this.label.on('input', function (e) {
                        $this.inputValueControl($(this));
                    }).on('focus', function () {
                        $this.jq.addClass('ui-inputwrapper-focus');
                    }).on('blur', function () {
                        $this.jq.removeClass('ui-inputwrapper-focus');
                        $this.inputValueControl($(this));
                    });
                }
            }
        },

        inputValueControl: function (input) {
            if (input.val() != '')
                this.jq.addClass('ui-inputwrapper-filled');
            else
                this.jq.removeClass('ui-inputwrapper-filled');
        }
    });
}

if (PrimeFaces.widget.Chips) {
    PrimeFaces.widget.Chips = PrimeFaces.widget.Chips.extend({
        init: function (cfg) {
            this._super(cfg);

            var $this = this;
            if (this.jq.parent().hasClass('ui-float-label')) {
                this.jq.addClass('ui-inputwrapper');

                if ($this.jq.find('.ui-chips-token').length !== 0) {
                    this.jq.addClass('ui-inputwrapper-filled');
                }

                this.input.on('focus.ui-chips', function () {
                    $this.jq.addClass('ui-inputwrapper-focus');
                }).on('input.ui-chips', function () {
                    $this.inputValueControl();
                }).on('blur.ui-chips', function () {
                    $this.jq.removeClass('ui-inputwrapper-focus');
                    $this.inputValueControl();
                });

            }
        },

        inputValueControl: function () {
            if (this.jq.find('.ui-chips-token').length !== 0 || this.input.val() != '')
                this.jq.addClass('ui-inputwrapper-filled');
            else
                this.jq.removeClass('ui-inputwrapper-filled');
        }
    });
}

if (PrimeFaces.widget.DatePicker) {
    PrimeFaces.widget.DatePicker = PrimeFaces.widget.DatePicker.extend({
        init: function (cfg) {
            this._super(cfg);

            var $this = this;
            if (this.jq.parent().hasClass('ui-float-label') && !this.cfg.inline) {
                if (this.input.val() != '') {
                    this.jq.addClass('ui-inputwrapper-filled');
                }

                this.jqEl.off('focus.ui-datepicker blur.ui-datepicker change.ui-datepicker')
                    .on('focus.ui-datepicker', function () {
                        $this.jq.addClass('ui-inputwrapper-focus');
                    })
                    .on('blur.ui-datepicker', function () {
                        $this.jq.removeClass('ui-inputwrapper-focus');
                    })
                    .on('change.ui-datepicker', function () {
                        $this.inputValueControl($(this));
                    });
            }
        },

        inputValueControl: function (input) {
            if (input.val() != '')
                this.jq.addClass('ui-inputwrapper-filled');
            else
                this.jq.removeClass('ui-inputwrapper-filled');
        }
    });
}