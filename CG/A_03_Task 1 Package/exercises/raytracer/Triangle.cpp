#include "Triangle.hpp"
#include "Ray.hpp"
#include <memory>

namespace rt
{
Triangle::Triangle(const Vec3d &v0, const Vec3d &v1, const Vec3d &v2,
                   const Vec3d &uvw0, const Vec3d &uvw1, const Vec3d &uvw2)
{
  mVertices[0] = v0;
  mVertices[1] = v1;
  mVertices[2] = v2;
  mUVW[0] = uvw0;
  mUVW[1] = uvw1;
  mUVW[2] = uvw2;
}

bool
Triangle::closestIntersectionModel(const Ray &ray, double maxLambda, RayIntersection& intersection) const
{
	//Programming TASK 1: implement this method
	//Your code should compute the intersection between a ray and a triangle.

	//If you detect an intersection, the return type should look similar to this:
	//if(rayIntersectsTriangle)
	//{
	//  intersection = RayIntersection(ray,shared_from_this(),lambda,ray.normal,uvw);
	//  return true;
	//} 

	// Möller-Trumbore (www.scratchapixel.com/lessons/3d-basic-rendering/
	// ray-tracing-rendering-a-triangle/moller-trumbore-ray-triangle-intersection)

	//  P is intersection point of triangle and ray
	//    P = w*a + u*b + v*c
	//    1 = w + u + v => w = 1 - u - v
	// I  P = (1−u−v)*a + u*b + v*c = a + u*(b - a) + v*(c - a)

	// II P = o + t*d

	// I == II 
	// o−a == −t*d + u(b−a) + v(c−a)

	// the term o-a can be looked at as a transformation moving the triangle 
	// from its original world space position to the origin

	// the other side of the equation has for effect to transform the intersection point 
	// from x, y, z space to "tuv-space"
	//                  _ _     
	// so we can write | t |    _          _
	//                 | u | * |_-d b-a c-a_| = o - a
	//                 |_v_|

	// the Möller - Trumbore Algorithm works with some mathematical tricks, 
	// like Cramer's rule and (determinant of a 3x3 matrix) = triple product (meaning (Vector1 cross Vector2) dot (Vektor3)

	// vertices of the triangle
	Vec3d a = this->mVertices[0]; 
	Vec3d b = this->mVertices[1];
	Vec3d c = this->mVertices[2];

	Vec3d o = ray.origin();
	Vec3d d = ray.direction();

	double u;
	double v;
	double t;

	Vec3d ab = b - a;
	Vec3d ac = c - a;

	// triangle normal
	Vec3d n = cross(d, ac);

	// determinant
	// det(-d ab ac) = (-d cross ab) dot ac = (d cross ac) dot ab = n dot ab
	double det = dot (n, ab);

	// if the determinant is (near) 0, 
	// the triangle and the ray are parallel
	// -> catch case of ray lying in the triangle and never hits the plane of the triangle
	if (fabs(det) < Math::safetyEps()) {
		return false;
	}

	double invDet = 1 / det;

	// Cramer's rule
	// we know the vector (o-a) = matrix(-d ab ac) * vector(t, u, v)
	// now we replace each column by the vector o-a once
	// matrix mt = matrix(o-a ab ac), matrix mu = matrix(-d o-a ac), matrix mv = matrix(-d ab o-a)
	//  => now cramer's rule says t = det(mt)/det(-d ab ac), u = det(mu)/det(-d ab ac) and v = det(mv)/det(-d ab ac)

	// u = 1/det * det(-d  o-a c-a) = 1/det * det(-d o-a ac)
	// det(-d o-a ac) = (-d cross o-a) dot ac = (d cross ac) dot o-a = n dot o-a
	// compute u and reject it if it is greater one or lower 0
	u = dot(n, o-a) * invDet;
	if (u < 0 || u > 1) {
		return false;
	}

	// v = 1/det * det(-d  b-a o-a) = 1/det * det(-d ab o-a)
	// det(-d ab o-a) = (-d cross ab) dot o-a = (o-a cross ab) dot d
	// compute v and reject it if it is lower than 0 or if u+v is greater than 1
	// because 1 = w + u + v and 0 <= w, u, v <= 1
	v = dot(d, cross(o - a, ab)) * invDet;
	if (v < 0 || u + v > 1) {
		return false;
	}

	// t = 1/det * det(o-a ab ac)
	// det(o-a ab ac) = (o-a cross ab) dot ac
	t = dot(ac, cross(o - a, ab)) * invDet;

	if (t <= maxLambda && t >= 0) {
		intersection = RayIntersection(ray, shared_from_this(), t, n, Vec3d(0, 0, 0));
		return true;
	}
	
  return false;
}

BoundingBox Triangle::computeBoundingBox() const
{
  BoundingBox bbox;
  bbox.expandByPoint(mVertices[0]);
  bbox.expandByPoint(mVertices[1]);
  bbox.expandByPoint(mVertices[2]);
  return bbox;
}

} //namespace rt
