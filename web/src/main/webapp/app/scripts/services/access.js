'use strict';

/**
 * @ngdoc service
 * @name oncokb.access
 * @description
 * # access
 * Service in the oncokb.
 */
angular.module('oncokb')
  .service('access', function access($rootScope, storage, config, users) {
    var self = this;
    var userRoles = config.userRoles;
    var accessLevel = config.accessLevel;
    var loginCallback = '';

    function getUserInfo(callback) {
        storage.requireAuth(true).then(function(){
            gapi.client.load('plus','v1', function(){
                gapi.client.plus.people.get({
                    'userId' : 'me'
                }).execute(user);
            });
        });
    }

    function user(userInfo) {
        var user = {};
        if(userInfo.emails) {
            for (var i = 0; i < userInfo.emails.length; i++) {
                if(userInfo.emails[i].type === 'account'){
                    user.email = userInfo.emails[i].value;
                    break;
                }
            }
        }

        users.setMe(user.email);
        user = users.getMe();

        if(userInfo.image && userInfo.image.url) {
            user.avatar = userInfo.image.url;
        }

        $rootScope.user = user;
        loginCallback();
    }
    return {
        authorize: function(accessLevel, role) {
            if(role === undefined)
                role = $rootScope.user.role;
            return accessLevel & role;
        },

        isLoggedIn: function(user) {
            if(user === undefined)
                user = $rootScope.user;
            return user.role === userRoles.user || user.role === userRoles.curator || user.role === userRoles.admin;
        },

        login: function(callback) {
            loginCallback = callback;
            getUserInfo();
        },

        logout: function(success, error) {
            $rootScope.user = {
                role: userRoles.public
            }
        }
    };
  });