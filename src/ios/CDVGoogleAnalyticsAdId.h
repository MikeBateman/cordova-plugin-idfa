#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>
#import "GAI.h"

@interface CDVGoogleAnalyticsAdId : CDVPlugin {
}

- (void) getAdId:(CDVInvokedUrlCommand*)command;

@end
