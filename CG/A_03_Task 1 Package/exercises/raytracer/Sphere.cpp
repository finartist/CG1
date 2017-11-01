#include "Sphere.hpp"
#include "Ray.hpp"
#include <memory>

namespace rt
{

bool
Sphere::closestIntersectionModel(const Ray &ray, double maxLambda, RayIntersection& intersection) const
{

	// Programming TASK 1: implement this method
	//Your code should compute the intersection between a ray and a unit sphere, with radius = 1, centered at origin (0,0,0);
	//(o+t*d - c)^2 - r^2= 0 --> (o+t*d)^2 -1 = 0

	//t == lambda
	//normale == vektor von mittelpunkt der kugel zu schnittpunkt

	//If you detect an intersection, the return type should look similar to this:
	//if(rayIntersectsSphere)
	//{
	//  intersection = RayIntersection(ray,shared_from_this(),lambda,ray.pointOnRay(lambda),uvw);
	//  return true;
//}

	Vec3d d = ray.direction();
	Vec3d o = ray.origin();

	// initializing t with negative value, because if no valid t found, function will return false
	double t1 = -1;
	double t2 = -1;
	double lambda;

	// Coefficients of the square equation deriving from (o+t*d)^2
	Vec3d sqrEqu = Vec3d(d.x()*d.x() + d.y()*d.y() + d.z()*d.z(), 2 * o.x()*d.x() + 2 * o.y()*d.y() + 2 * o.z()*d.z(),  o.x()*o.x() + o.y()*o.y() + o.z()*o.z() - 1);

	
	if (sqrEqu.x() != 0) {
		// divide every part of the sqr. equation by the coefficient in front of t^2, so we can use pq-formula
		sqrEqu.x() /= sqrEqu.x();
		sqrEqu.y() /= sqrEqu.x();
		sqrEqu.z() /= sqrEqu.x();

		double radicant = (sqrEqu.y() / 2)*(sqrEqu.y() / 2) - sqrEqu.z();
		if (radicant < 0) {
			return false;
		}
		// 2 solutions for t
		t1 = -(sqrEqu.y() / 2) + std::sqrt(radicant);
		t2 = -(sqrEqu.y() / 2) - std::sqrt(radicant);
	}

	else {
		return false;
	}

	// if there are 2 valid t, use the smaller, because it's closer to the ray's origin
	if (!(t1 < 0 || t1 > maxLambda) && !(t2 < 0 || t2 > maxLambda)) {
		lambda = t1 >= t2 ? t2 : t1;
		intersection = RayIntersection(ray, shared_from_this(), lambda, ray.pointOnRay(lambda), Vec3d(0, 0, 0));
		return true;
	}
  
	 // although the function should return false beforehand if there is no valid t, 
	 // this assures it will definitly return something if I made a mistake
  return false;
}

BoundingBox Sphere::computeBoundingBox() const
{
  BoundingBox box;
  box.setMin(Vec3d(-1,-1,-1));
  box.setMax(Vec3d(1,1,1));
  return box;
}

} //namespace rt
