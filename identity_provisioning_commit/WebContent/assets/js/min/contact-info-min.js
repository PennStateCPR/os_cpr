var commit=commit||{};(function($){"use strict";commit.contactinfo={selectors:{form:"#contactinfo",email:"#email",primaryEmail:"#primaryEmail",emailPopover:"#emailPopover",emailPopoverContent:"#emailPopoverContent",phonePopover:"#phonePopover",phonePopoverContent:"#phonePopoverContent",international:"#internationalNumber"},utils:{},validator:{},phone:{us:{required:!0,phoneUS:!0},international:{required:!0,phoneINT:!0}},getPhoneType:function(){var international=$(this.selectors.international);return international.is(":checked")?this.phone.international:this.phone.us},validate:function(){var selectors=this.selectors;this.validator=new commit.validator({selectors:selectors,rules:{email:{required:!0,email:!0},phoneNumber:{required:!0,phoneUS:!0}},messages:{email:{required:"Please enter your email."},phoneNumber:{required:"Please enter your phone number."}},formModal:!0,formPopover:!0,focus:!0,verify:!0})},onInternationalSelect:function(){var selectors,form,validator,phoneType;selectors=this.selectors,form=$(selectors.form),validator=form.data.validator,phoneType=this.getPhoneType(),form.data().hasOwnProperty("validator")&&(form.data().validator.settings.rules.phoneNumber=phoneType)},initializeViewPopovers:function(){var emailPopover,emailPopoverContent,phonePopover,phonePopoverContent;emailPopover=$(this.selectors.emailPopover),emailPopoverContent=$(this.selectors.emailPopoverContent).html(),phonePopover=$(this.selectors.phonePopover),phonePopoverContent=$(this.selectors.phonePopoverContent).html(),this.utils.popover(emailPopover,emailPopoverContent),this.utils.popover(phonePopover,phonePopoverContent)},attachModalListeners:function(){var email,primaryEmail,dialog;email=$(this.selectors.email),primaryEmail=$(this.selectors.primaryEmail),dialog=this.validator.dialog,dialog.on("show",_.bind(function(){primaryEmail.html($.trim(email.val()))},this)),dialog.on("hidden",_.bind(function(){primaryEmail.html("")},this))},initialize:function(){_.bindAll(this),this.utils=commit.utils,this.initializeViewPopovers(),this.validate(),this.attachModalListeners()}},commit.contactinfo.initialize()})(jQuery);